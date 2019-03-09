package com.wjybxx.example;

import com.google.protobuf.MessageLite;
import com.wjybxx.generatedmessage.PCSMessage;
import com.wjybxx.protobuf.MessageDispatcher;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleServerHandler extends SimpleChannelInboundHandler<MessageLite> {

    private static final Logger logger= LoggerFactory.getLogger(ExampleServerHandler.class);

    private final MessageDispatcher messageDispatcher;

    public ExampleServerHandler(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageLite msg) throws Exception {
        // 将消息分发出去
        messageDispatcher.onMessage(ctx.channel(), msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 在连接可用时，向客户端发送一条信息
        PCSMessage.server_client_first_message message = PCSMessage.server_client_first_message.newBuilder()
                .setName("wjybxx")
                .setUid(1008611)
                .build();

        ctx.writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.info("exceptionCaught",cause);
    }
}
