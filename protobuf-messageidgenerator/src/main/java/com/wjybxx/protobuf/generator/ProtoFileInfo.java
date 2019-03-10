package com.wjybxx.protobuf.generator;

import java.util.Collections;
import java.util.List;

/**
 * 单个proto文件信息
 */
public class ProtoFileInfo {
    /**
     * 文件名
     */
    private final String fileName;
    /**
     * 文件中定义的package
     */
    private final String packageName;
    /**
     * java包名
     */
    private final String javaPackageName;
    /**
     * java外部类名
     */
    private final String javaOuterClassName;

    /**
     * 该文件中所有消息
     * 1.按照文件顺序的，因此是List
     * 2.一定是不重复的,因此不需要判重
     */
    private final List<MessageBean> messageBeanList;

    public ProtoFileInfo(String fileName, String packageName, String javaPackageName, String javaOuterClassName, List<MessageBean> messageBeanList) {
        this.fileName = fileName;
        this.packageName = packageName;
        this.javaPackageName = javaPackageName;
        this.javaOuterClassName = javaOuterClassName;
        this.messageBeanList = Collections.unmodifiableList(messageBeanList);
    }

    public String getFileName() {
        return fileName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getJavaPackageName() {
        return javaPackageName;
    }

    public String getJavaOuterClassName() {
        return javaOuterClassName;
    }

    public List<MessageBean> getMessageBeanList() {
        return messageBeanList;
    }
}
