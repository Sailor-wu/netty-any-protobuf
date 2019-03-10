package com.wjybxx.protobuf.start;

import com.wjybxx.protobuf.codegenerator.JavaEnumGenerator;
import com.wjybxx.protobuf.codegenerator.TypeScriptEnumGenerator;
import com.wjybxx.protobuf.generator.*;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * 启动直接需要配置一下 generator.properties
 * 配置自己需要的属性
 *
 */
public class GeneratorStart {

    public static void main(String[] args) throws IOException {
        Properties properties= ResourceLoader.loadConfig("generator.properties");
        if(null==properties){
            throw new IllegalArgumentException("can't find generator.properties");
        }

        MessageRepository messageRepository = ProtoBufFileUtils.findAllMessage(properties);

        // 这里使用自己需要的策略(模式)
        MessageIdGenerator messageIdGenerator = new HashMessageIdGenerator();
//        messageIdGenerator = new SequenceMessageIdGenerator();
        System.out.println("start generateMessageId, strategy="+messageIdGenerator.getClass().getSimpleName());
        messageIdGenerator.generateMessageId(properties,messageRepository);

        // 生成代码各端代码 (Java)
        System.out.println("start generate java enum.");
        new JavaEnumGenerator().generateCode(properties,messageRepository);

        // TS
//        System.out.println("\nstart generate typescript enum.");
//        new TypeScriptEnumGenerator().generateCode(properties,messageRepository);

        System.out.println("success.");
    }
}
