# netty-any-protobuf
netty支持任意多个protoBuf协议编解码方案，易实行。

 自定义变长帧协议，其帧结构为：
 * +--------------+------------+-----------------
 * | protoLength  |  messageId |  messageBytes  |
 * +--------------+------------+-----------------

  其中： 
* protoLength:int，占用4个字节，表示messageId + messageBytes的长度。
* messageId: int，占用4个字节，消息体的唯一id。
* messageBytes: bytes[],长度为(protoLength-4)，消息的真正内容。

# 编解码原理：
* 解码时，每一个message对应一个唯一的messageId，每一个messageId都可以得到它的消息体的parser(编解码方法).
* 编码时，每一个message都可以找到它唯一的messageId。

# 关键在于messageId分配，需要保证以下几点：
* 不可以重复
* 易扩展
* 稳定(尽可能的保持之前的messageId)

  messageId主要通过工具为各个端生成，减少手动操作，手动操作过于繁琐，且可维护性太差。方式之间各有优缺点，后面详细说明。
* 计算message的hashcode，
* 为每一个文件分配一个区间段，文件内的message按照顺序分配id。
* 其他，暂未想到。应该也存在

* 2019年3月8日20:25:48 版本，先实现了hash分配的方式。
* 2019年3月9日15:27:48 版本，实现了文件顺序分配的方式。

# 模块组织
* app-core是核心包，是项目中其他模块依赖的对象。它持有messageId生成器生成的枚举类，并实现了编解码过程。并提供了一个example示例。
* protobuf-generatedmessage 是存放protoBuf生成的java代码的。
* protobuf-messageidgenerator 是工具module，遍历指定的proto文件，将文件内的message读取出来，并分配id，然后写入各自需要的文件中。
(在这里app-core包中的MessageEnum枚举文件是工具根据模板文件为java端生成的枚举文件，需要其它语言的文件，可以自己实现，后期我会写一个TypeScript的类文件)

# 食用方法
* 需要先配置generator.properties文件。
* 有resource下有两个模板类，分别用于生成新的Java和TypeScript文件。
* 资源加载时优先在本地加载，其次在jar包目录环境下加载(本地文件可以覆盖jar包配置文件)。
* 其它语言类似，根据实际情况写一个模板文件，然后替换指定部分内容即可。