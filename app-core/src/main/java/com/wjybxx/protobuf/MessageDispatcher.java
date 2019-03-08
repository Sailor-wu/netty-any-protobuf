package com.wjybxx.protobuf;

import com.google.protobuf.MessageLite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息分发器，用于将消息分发到各个消息handler
 *
 * 要求所有的初始化工作都happens-before消息的处理工作，这是一种保证可见性的好方式
 * {@link #registerHandler(Class, MessageHandler)} happens-before {@link #onMessage(MessageLite)}
 */
public class MessageDispatcher {

    private static final Logger logger= LoggerFactory.getLogger(MessageDispatcher.class);
    /**
     * messageClass->handler
     * 消息可能较多，这里初始化1024空间
     */
    private final Map<Class<?>,MessageHandler<?>> handlerMapping=new HashMap<>(1024);

    /**
     * 注册一个消息处理器
     * @param messageClass
     * @param handler
     * @param <T>
     */
    public <T extends MessageLite> void registerHandler(Class<T> messageClass,MessageHandler<T> handler){
        handlerMapping.put(messageClass,handler);
    }

    /**
     * 当收到一个消息
     * @param messageLite
     */
    public void onMessage(MessageLite messageLite){
        try {
            @SuppressWarnings("unchecked")
            MessageHandler<MessageLite> messageHandler = (MessageHandler<MessageLite>) handlerMapping.get(messageLite.getClass());
            if (null==messageHandler){
                logger.error("message handler is not registered, msgName={}",messageLite.getClass().getSimpleName());
                return;
            }
            messageHandler.onMessage(messageLite);
        } catch (Exception e) {
            logger.info("handle msg failed, msgName={}",messageLite.getClass().getSimpleName(),e);
        }
    }
}
