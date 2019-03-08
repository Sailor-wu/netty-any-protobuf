package com.wjybxx.protobuf.codegenerator;

import com.wjybxx.protobuf.generator.MessageRepository;

import java.io.IOException;
import java.util.Properties;

/**
 * 生成各端代码的接口
 */
public interface CodeGenerator {

    /**
     *  @param properties
     * @param messageRepository
     */
    void generateCode(Properties properties, MessageRepository messageRepository) throws IOException;

}
