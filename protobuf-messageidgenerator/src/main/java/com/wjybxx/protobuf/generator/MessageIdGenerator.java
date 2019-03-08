package com.wjybxx.protobuf.generator;

import java.io.IOException;
import java.util.Properties;

/**
 * protoBuf messageId 生成器(策略模式)
 * 你可以选择自己需要的方式分配messageId
 */
public interface MessageIdGenerator {
    /**
     * 生成messageId
     * @param properties
     * @param messageRepository
     */
    void generateMessageId(Properties properties, ProtoMessageRepository messageRepository) throws IOException;
}
