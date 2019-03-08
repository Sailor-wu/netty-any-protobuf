package com.wjybxx.protobuf.generator;

import java.io.*;
import java.util.Properties;

public abstract class AbstractMessageIdGenerator implements MessageIdGenerator{

    private static final String templateFileName = "MessageEnumTemplate.txt";
    private static final String contentMark="//{content}";

    /**
     * 写java枚举文件 MessageEnum
     *
     * @param messageRepository
     */
    protected  final void writeJavaMessageEnum(Properties properties, ProtoMessageRepository messageRepository) throws IOException {
        String messageEnumPath=properties.getProperty("messageEnumDir") + File.separator + "MessageEnum.java";
        File file=new File(messageEnumPath);
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();

        InputStream templateFileStream = ResourceLoader.loadAsStream(templateFileName);
        if (null==templateFileStream){
            throw new IOException("can't find " +templateFileName);
        }

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(templateFileStream));
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        try {
            String line;
            // 是否填充了
            boolean padded=false;
            while ((line=bufferedReader.readLine())!=null){
                if (!padded && line.trim().equals(contentMark)){
                    paddingJavaEnum(messageRepository,bufferedWriter);
                    padded=true;
                }else {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
        }finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    private void paddingJavaEnum(ProtoMessageRepository messageRepository, BufferedWriter bufferedWriter) throws IOException {
        for (ProtoFileInfo protoFileInfo:messageRepository.values()){

            for (MessageBean messageBean:protoFileInfo.getMessageBeanList()){

                // 检测必须赋值过messageId了
                if (!messageBean.isAssigned()){
                    throw new IllegalArgumentException("message "+messageBean.getMessageName()+ "'s messageId is not assigned.");
                }

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

    // TODO 其它语言文件
}
