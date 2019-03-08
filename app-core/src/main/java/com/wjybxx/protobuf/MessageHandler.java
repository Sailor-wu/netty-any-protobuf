package com.wjybxx.protobuf;

import com.google.protobuf.MessageLite;

/**
 * 定义消息处理的方法
 * 策略模式的应用，为了消除大量的if else 判定
 */
public interface MessageHandler<T extends MessageLite> {
    /**
     * 当收到一个消息时
     * @param message
     * @throws Exception
     */
    void onMessage(T message) throws Exception;

}
