package com.wjybxx.protobuf;

/**
 * 消息枚举，自动生成的文件
 */
public enum MessageEnum {

    //content
    ;
    /**
     * 消息id，必须唯一
     */
    private final int messageId;
    /**
     * java包名
     */
    private final String javaPackageName;

    /**
     * java外部类名字
     */
    private final String javaOuterClassName;

    /**
     * 消息名(类简单名)
     * {@link Class#getSimpleName()}
     */
    private final String messageName;

    MessageEnum(int messageId, String javaPackageName, String javaOuterClassName, String messageName) {
        this.messageId = messageId;
        this.javaPackageName = javaPackageName;
        this.javaOuterClassName = javaOuterClassName;
        this.messageName = messageName;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getJavaPackageName() {
        return javaPackageName;
    }

    public String getJavaOuterClassName() {
        return javaOuterClassName;
    }

    public String getMessageName() {
        return messageName;
    }

    @Override
    public String toString() {
        return "MessageEnum{" +
                "messageId=" + messageId +
                ", javaPackageName='" + javaPackageName + '\'' +
                ", javaOuterClassName='" + javaOuterClassName + '\'' +
                ", messageName='" + messageName + '\'' +
                '}';
    }
}
