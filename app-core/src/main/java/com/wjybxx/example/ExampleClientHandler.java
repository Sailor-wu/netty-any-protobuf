package com.wjybxx.example;

import com.google.protobuf.MessageLite;
import com.wjybxx.protobuf.MessageDispatcher;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link com.wjybxx.netty.protobufcodec.ByteToProtoBufDecoder}
 *
 * 这里收到的是由解码之后的消息对象
 *
 */
public class ExampleClientHandler extends SimpleChannelInboundHandler<MessageLite> {

    private static final Logger logger= LoggerFactory.getLogger(ExampleClientHandler.class);

    private final MessageDispatcher messageDispatcher;

    public ExampleClientHandler(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageLite msg) throws Exception {
        // 将消息分发出去
        messageDispatcher.onMessage(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.info("exceptionCaught",cause);
    }
}
