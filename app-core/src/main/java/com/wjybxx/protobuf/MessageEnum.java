package com.wjybxx.protobuf;

/**
 * 消息枚举，自动生成的文件
 */
public enum  MessageEnum {

    SERVER_CLIENT_FIRST_MESSAGE(10001,"com.wjybxx.generatedmessage","PCSMessage","server_client_first_message"),
    CLIENT_SERVER_PING(10002,"com.wjybxx.generatedmessage","PCSMessage","client_server_ping"),
    SERVER_CLIENT_PONG(10003,"com.wjybxx.generatedmessage","PCSMessage","server_client_pong"),
    SERVER_CLIENT_ONE_REQUEST(10004,"com.wjybxx.generatedmessage","PCSMessage","server_client_one_request"),
    SERVER_CLIENT_ONE_REQUEST_RESULT(10005,"com.wjybxx.generatedmessage","PCSMessage","server_client_one_request_result"),
    SERVER_SERVER_FIRST_MESSAGE(20001,"com.wjybxx.generatedmessage","PSSMessage","server_server_first_message"),
    SERVER_SERVER_PING(20002,"com.wjybxx.generatedmessage","PSSMessage","server_server_ping"),
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
