package com.wjybxx.protobuf.codegenerator;

import com.wjybxx.protobuf.generator.MessageRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * TypeScript 枚举文件生成
 */
public class TypeScriptEnumGenerator extends AbstractCodeGenerator{
    /**
     * messageIdEnum内容替换标记
     */
    private static final String messageIdEnumContent="//MessageIdEnumContent";
    /**
     * messageNameEnum内容替换标记
     */
    private static final String messageNameEnumContent="//MessageNameEnumContent";


    @Override
    protected String templateFileName(Properties properties) {
        return "TypeScriptEnumTemplate.txt";
    }

    @Override
    protected String targetFileDir(Properties properties) {
        return properties.getProperty("tsMessageEnumDir");
    }

    @Override
    protected String targetFileName(Properties properties) {
        return "MessageEnum.ts";
    }

    @Override
    protected void doGenerate(BufferedReader reader, BufferedWriter writer, Properties properties, MessageRepository messageRepository) throws IOException {

    }
}
