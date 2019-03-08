package com.wjybxx.protobuf.generator;

/**
 * 一个简单的包装对象，用于建立messageName 和 messageId之间的约束
 */
public class MessageBean {
    /**
     * 消息名字
      */
    private final String  messageName;
    /**
     * 消息的最终id
     */
    private int messageId;
    /**
     * 是否已赋值messageId了
     */
    private boolean assigned=false;

    public MessageBean(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageName() {
        return messageName;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
        this.assigned=true;
    }

    public boolean isAssigned() {
        return assigned;
    }
}
