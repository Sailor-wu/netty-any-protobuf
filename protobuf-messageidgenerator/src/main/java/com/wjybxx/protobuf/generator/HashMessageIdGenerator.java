package com.wjybxx.protobuf.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * hash的方式生成messageId
 *
 * 优点：简单、稳定，可以安全的兼容旧版本(重要)。
 * 确定：可能出现hash冲突，需要将冲突的消息打印出来，进行更名。
 */
public class HashMessageIdGenerator extends AbstractMessageIdGenerator{

    /**
     * 用于检测冲突
     */
    private final HashMap<Integer,String> hashCode2messageName=new HashMap<>(1024);

    @Override
    public void generateMessageId(Properties properties, ProtoMessageRepository messageRepository) throws IOException {
        for (ProtoFileInfo protoFileInfo:messageRepository.values()){
            for (MessageBean messageBean:protoFileInfo.getMessageBeanList()){
                String messageName=messageBean.getMessageName();
                int hashCode=messageName.hashCode();
                if (hashCode2messageName.containsKey(hashCode)){
                    String info=String.format("messageName %s and %s  has same hashcode.",
                            messageName,
                            hashCode2messageName.get(hashCode));
                    throw new IllegalArgumentException(info);
                }
                hashCode2messageName.put(hashCode,messageName);
                messageBean.setMessageId(hashCode);
            }
        }
        // 写java枚举文件
        writeJavaMessageEnum(properties,messageRepository);
    }
}
