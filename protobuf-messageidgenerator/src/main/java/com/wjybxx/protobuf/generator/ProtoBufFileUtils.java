package com.wjybxx.protobuf.generator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProtoBufFileUtils {

    /**
     * 找出指定文件夹下，指令文件内的所有的message
     * @param properties
     * @return
     * @throws IOException
     */
    public static MessageRepository findAllMessage(Properties properties) throws IOException {
        String protoBufDir = properties.getProperty("protoBufDir");
        String[] protoFileNames=properties.getProperty("protoBufFiles").split(",");
        MessageRepository repository=new MessageRepository();
        for (String fileName:protoFileNames){
            // 额外的空格
            if (fileName.trim().equals("")){
                continue;
            }
            repository.addProtoFileInfo(readOneFile(protoBufDir,fileName));
        }
        return repository;
    }

    /**
     * 读取一个protoBuf文件，按顺序检索出所有的messageName
     * @param protoBufDir
     * @param fileName
     * @return
     * @throws IOException
     */
    private static ProtoFileInfo readOneFile(String protoBufDir,String fileName) throws IOException {
        String fileAbsPath=protoBufDir+File.separator + fileName;
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(fileAbsPath)))){
            String line;
            String packageName=null;
            String javaPackageName=null;
            String javaOuterClassName=null;
            // 估算最多消息数
            List<MessageBean> messageBeanList=new ArrayList<>(512);
            while ((line=bufferedReader.readLine())!=null){
                line=line.trim();

                // package属性
                if (null==packageName && line.startsWith(OptionNames.PACKAGE)){
                    packageName=parsePackage(line);
                    continue;
                }

                // java包名
                if (null==javaPackageName && line.contains(OptionNames.JAVA_PACKAGE)){
                    javaPackageName= parseOptionValue(line);
                    continue;
                }

                // java外部类名
                if (null==javaOuterClassName && line.contains(OptionNames.JAVA_OUTER_CLASSNAME)){
                    javaOuterClassName= parseOptionValue(line);
                    continue;
                }

                // 消息体行
                if (line.startsWith(OptionNames.MESSAGE)){
                    String messageName = parseMessageName(line);
                    messageBeanList.add(new MessageBean(messageName));
                }
            }

            if (packageName==null){
                throw new IllegalArgumentException("package property is missing.");
            }

            if (javaPackageName==null){
                throw new IllegalArgumentException("javaPackageName property is missing.");
            }
            if (javaOuterClassName==null){
                throw new IllegalArgumentException("javaOuterClassName property is missing.");
            }
            return new ProtoFileInfo(fileName, packageName, javaPackageName,javaOuterClassName, messageBeanList);
        }
    }


    private static String parsePackage(String line){
        // 遇见第一个空格或分号切割
        return line.substring(OptionNames.PACKAGE.length()).trim().split("[ ;]",2)[0];
    }

    /**
     * 解析option属性的值
     * @param line
     * @return
     */
    private static String parseOptionValue(String line){
        // 取出第一对""之间是属性
        int index=line.indexOf('"');
        String firstString = line.substring(index + 1);
        return firstString.substring(0,firstString.indexOf('"'));
    }

    /**
     * 解析消息行的消息名
     * @param line
     * @return
     */
    private static String parseMessageName(String line){
        // 遇见第一个 ' ' 或 '{' 或 '/' 切割
        return line.substring(OptionNames.MESSAGE.length()).trim().split("[ {/]",2)[0];
    }

    public static void main(String[] args) throws IOException {
        String dir="E:\\workSpace-tools\\protobuf-generatedmessage\\src\\protobuf";
        String fileName="client_server.proto";
        ProtoFileInfo protoFileInfo = readOneFile(dir, fileName);
        System.out.println();
    }
}
