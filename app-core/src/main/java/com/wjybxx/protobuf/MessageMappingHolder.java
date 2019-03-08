package com.wjybxx.protobuf;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息映射管理
 *
 * 使用静态初始化的方式，利用空间换取安全性(可见性)和性能,
 *
 * 要在应用启动的最开始的时候加载该类
 */
public class MessageMappingHolder {

    private static final Logger logger= LoggerFactory.getLogger(MessageMappingHolder.class);

    private static final Map<Integer,Parser<?>> messageId2ParserMap=new HashMap<>(1024);

    private static final Map<Class<?>,Integer> messageClass2IdMap=new HashMap<>(1024);

    static {
        for (MessageEnum messageEnum:MessageEnum.values()){
            try {
                Class<? extends MessageLite> messageClass=ProtoBufUtils.findMessageClass(messageEnum.getJavaPackageName(),
                        messageEnum.getJavaOuterClassName(),messageEnum.getMessageName());
                Parser<? extends MessageLite> parser=ProtoBufUtils.findMessageParser(messageClass);
                messageClass2IdMap.put(messageClass,messageEnum.getMessageId());
                messageId2ParserMap.put(messageEnum.getMessageId(),parser);
            }catch (ReflectiveOperationException e){
                // 内部异常捕获，是为了记录更详细的信息
                logger.error("can't find class or parser,messageInfo={}",messageEnum.toString(),e);
            }
        }
    }

    /**
     * 根据消息id 获取对应的parser
     * @param messageId
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Parser<T> getParser(int messageId){
        return (Parser<T>) messageId2ParserMap.get(messageId);
    }

    /**
     * 获取对象对应的messageId
     * @param message
     * @param <T>
     * @return
     */
    public static <T extends MessageLite> int getMessageId(T message){
        @SuppressWarnings("unchecked")
        Class<T> messageLiteClass= (Class<T>) message.getClass();
        return getMessageId(messageLiteClass);
    }

    /**
     * 获取消息类对应的messageId
     * @param messageClass
     * @param <T>
     * @return
     */
    public static <T extends MessageLite> int getMessageId(Class<T> messageClass){
        return messageClass2IdMap.get(messageClass);
    }

}
