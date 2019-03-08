package com.wjybxx.protobuf.codegenerator;

import com.wjybxx.protobuf.generator.MessageBean;
import com.wjybxx.protobuf.generator.MessageRepository;
import com.wjybxx.protobuf.generator.ProtoFileInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 写java枚举文件 MessageEnum
 */
public class JavaEnumGenerator extends AbstractCodeGenerator {

    private static final String contentMark="//content";

    @Override
    protected String templateFileName(Properties properties) {
        return "JavaEnumTemplate.txt";
    }

    @Override
    protected String targetFileDir(Properties properties) {
        return properties.getProperty("javaMessageEnumDir");
    }

    @Override
    protected String targetFileName(Properties properties) {
        return "MessageEnum.java";
    }

    @Override
    protected void doGenerate(BufferedReader reader, BufferedWriter writer, Properties properties, MessageRepository messageRepository) throws IOException {
        String line;
        // 是否填充了
        boolean padded=false;
        while ((line=reader.readLine())!=null){
            if (!padded && line.trim().equals(contentMark)){
                paddingJavaEnum(messageRepository,writer);
                padded=true;
            }else {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private void paddingJavaEnum(MessageRepository messageRepository, BufferedWriter bufferedWriter) throws IOException {
        for (ProtoFileInfo protoFileInfo:messageRepository.values()){
            for (MessageBean messageBean:protoFileInfo.getMessageBeanList()){
                // MessageEnum(int messageId, String javaPackageName, String javaOuterClassName, String messageName)
                String paddingLine=String.format("    %s(%d,\"%s\",\"%s\",\"%s\"),",
                        messageBean.getMessageName().toUpperCase(),
                        messageBean.getMessageId(),
                        protoFileInfo.getJavaPackageName(),
                        protoFileInfo.getJavaOuterClassName(),
                        messageBean.getMessageName());
                bufferedWriter.write(paddingLine);
                bufferedWriter.newLine();
            }
        }
    }
}
