package com.wjybxx.protobuf.generator;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * 顺序messageId生成器。
 * 其顺序的概念是指：每个文件占用一段messageId，文件内的 messageId是顺序递增的
 *
 * 优点：不会出现messageId冲突，一定是不同的，也足够简单。messageId可以表示额外的信息。
 * 缺点：当要兼容老版本的程序时，只有在文件的末尾添加新message是安全的。
 *      在中间插入和删除消息带来的破坏性太大。不过要遵守这一条其实也挺容易的。
 */
public class SequenceMessageIdGenerator implements MessageIdGenerator{

    @Override
    public void generateMessageId(Properties properties, MessageRepository messageRepository) throws IOException {
        List<ProtoFileInfo> values = messageRepository.values();

        for (int fileIndex=0;fileIndex<values.size();fileIndex++){
            ProtoFileInfo protoFileInfo=values.get(fileIndex);
            List<MessageBean> messageBeanList=protoFileInfo.getMessageBeanList();
            for (int messageIndex=0;messageIndex<messageBeanList.size();messageIndex++){
                messageBeanList.get(messageIndex).setMessageId(combine2MessageId(fileIndex,messageIndex));
            }
        }
    }

    private int combine2MessageId(int fileIndex,int messageIndex){
        // 文件内10000个消息足够了，这样的方式可读性比较好
        // 移位的方式可读性差一点
        return (fileIndex+1)*10000 + (messageIndex+1);
    }
}
