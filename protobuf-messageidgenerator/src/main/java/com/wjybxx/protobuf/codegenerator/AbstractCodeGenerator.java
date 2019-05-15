package com.wjybxx.protobuf.codegenerator;

import com.wjybxx.protobuf.generator.MessageBean;
import com.wjybxx.protobuf.generator.MessageRepository;
import com.wjybxx.protobuf.generator.ProtoFileInfo;
import com.wjybxx.protobuf.generator.ResourceLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public abstract class AbstractCodeGenerator implements CodeGenerator{

    @Override
    public final void generateCode(Properties properties, MessageRepository messageRepository) throws IOException {
        ensureMessageIdAssigned(messageRepository);

        String targetFilePath=targetFileDir(properties) + File.separator + targetFileName(properties);
        System.out.println("targetFilePath="+targetFilePath);
        File file=new File(targetFilePath);
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();

        String templateFileName = templateFileName(properties);
        InputStream templateFileStream = ResourceLoader.loadAsStream(templateFileName);
        if (null==templateFileStream){
            throw new IOException("can't find " +templateFileName);
        }

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(templateFileStream, StandardCharsets.UTF_8));
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

        try{
            doGenerate(bufferedReader,bufferedWriter,properties,messageRepository);
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    /**
     * 检测所有的message必须赋值过messageId了
     * @param messageRepository
     */
    private void ensureMessageIdAssigned(MessageRepository messageRepository){
        for (ProtoFileInfo protoFileInfo:messageRepository.values()){
            for (MessageBean messageBean:protoFileInfo.getMessageBeanList()){
                if (!messageBean.isAssigned()){
                    throw new IllegalArgumentException("message "+messageBean.getMessageName()+ "'s messageId is not assigned.");
                }
            }
        }
    }

    protected abstract String templateFileName(Properties properties);

    protected abstract String targetFileDir(Properties properties);

    protected abstract String targetFileName(Properties properties);

    /**
     * 执行代码生成
     * @param reader autoclose
     * @param writer autoclose
     * @param properties
     * @param messageRepository
     * @throws IOException
     */
    protected abstract void doGenerate(BufferedReader reader, BufferedWriter writer, Properties properties, MessageRepository messageRepository) throws IOException;

}
