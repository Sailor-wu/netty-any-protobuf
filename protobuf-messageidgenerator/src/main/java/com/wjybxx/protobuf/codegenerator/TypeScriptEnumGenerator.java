package com.wjybxx.protobuf.codegenerator;

import com.wjybxx.protobuf.generator.MessageBean;
import com.wjybxx.protobuf.generator.MessageRepository;
import com.wjybxx.protobuf.generator.ProtoFileInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * TypeScript 枚举文件生成
 */
public class TypeScriptEnumGenerator extends AbstractCodeGenerator{
    /**
     * messageEnum内容替换标记
     */
    private static final String messageEnumPadding="//MessageEnumPadding";
    /**
     * messageId2classMapping内容替换标记
     */
    private static final String messageId2ClassMapping ="//messageId2ClassMapping";
    /**
     * messageName2idMapping内容替换编辑
     */
    private static final String messageName2IdMapping ="//messageName2IdMapping";

    @Override
    protected String templateFileName(Properties properties) {
        return "TypeScriptEnumTemplate.ts";
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
        String line;
        boolean messageEnumPadded=false;
        boolean messageIdMapPadded=false;
        boolean messageName2idMapPadded=false;

        while ((line=reader.readLine())!=null){

            if (!messageEnumPadded && line.trim().equals(messageEnumPadding)){
                paddingMessageEnum(writer,properties,messageRepository);
                messageEnumPadded=true;
                continue;
            }

            if (!messageIdMapPadded && line.trim().equals(messageId2ClassMapping)){
                paddingMessageId2classMapping(writer,properties,messageRepository);
                messageIdMapPadded=true;
                continue;
            }

            if (!messageName2idMapPadded && line.trim().equals(messageName2IdMapping)){
                paddingMessageName2IdMapping(writer,properties,messageRepository);
                messageName2idMapPadded=true;
                continue;
            }

            writer.write(line);
            writer.newLine();
        }
    }

    /**
     * 填充枚举
     * @param bufferedWriter
     * @param properties
     * @param messageRepository
     * @throws IOException
     */
    private void paddingMessageEnum(BufferedWriter bufferedWriter,Properties properties, MessageRepository messageRepository) throws IOException {
        for (ProtoFileInfo protoFileInfo:messageRepository.values()){
            for (MessageBean messageBean:protoFileInfo.getMessageBeanList()){
                // padding like UpperCase(messageName):messageId,
                String paddingLine=String.format("        %s=%d,",
                        messageBean.getMessageName().toUpperCase(),
                        messageBean.getMessageId());
                bufferedWriter.write(paddingLine);
                bufferedWriter.newLine();
            }
        }
    }

    /**
     * 填充messsageId到类的映射
     * @param bufferedWriter
     * @param properties
     * @param messageRepository
     * @throws IOException
     */
    private void paddingMessageId2classMapping(BufferedWriter bufferedWriter,Properties properties, MessageRepository messageRepository) throws IOException {
        for (ProtoFileInfo protoFileInfo:messageRepository.values()){
            for (MessageBean messageBean:protoFileInfo.getMessageBeanList()){
                // padding like messageId:messageName,
                String paddingLine=String.format("            %d:%s,",
                        messageBean.getMessageId(),
                        messageBean.getMessageName());
                bufferedWriter.write(paddingLine);
                bufferedWriter.newLine();
            }
        }
    }

    /**
     * 填充类名到messageId的映射
     * @param bufferedWriter
     * @param properties
     * @param messageRepository
     * @throws IOException
     */
    private void paddingMessageName2IdMapping(BufferedWriter bufferedWriter,Properties properties, MessageRepository messageRepository) throws IOException {
        for (ProtoFileInfo protoFileInfo:messageRepository.values()){
            for (MessageBean messageBean:protoFileInfo.getMessageBeanList()){
                // padding like messageName:messageId,
                String paddingLine=String.format("            \"%s\":%d,",
                        messageBean.getMessageName(),
                        messageBean.getMessageId());
                bufferedWriter.write(paddingLine);
                bufferedWriter.newLine();
            }
        }
    }
}
