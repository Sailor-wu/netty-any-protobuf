package com.wjybxx.example;

import com.wjybxx.generatedmessage.PCSMessage;
import com.wjybxx.logger.LoggerUtils;
import com.wjybxx.protobuf.MessageDispatcher;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;

/**
 * 用于展示的服务器
 */
public class ExampleServer {

    public static void main(String[] args) throws InterruptedException, IOException {
        LoggerUtils.createLogFile("ExampleServer.log");

        EventLoopGroup bossGroup=new NioEventLoopGroup(1,new BossThreadFactory());
        EventLoopGroup workerGroup=new NioEventLoopGroup(1,new WorkerThreadFactory());
        int serverPort=12345;
        try {

            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // childHandler 是针对workerGroup的, handler是针对 bossGroup的
                    .childHandler(newServerInitializer())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_KEEPALIVE,false)
                    .childOption(ChannelOption.SO_RCVBUF,8192)
                    .childOption(ChannelOption.SO_SNDBUF,8192)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,false);

            ChannelFuture future = bootstrap.bind("localhost", serverPort).sync();
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static ChannelInitializer<SocketChannel> newServerInitializer(){
        return new ExampleServerInitializer(newServerMessageDispatcher());
    }

    /**
     * 注册服务器要处理的消息
     * @return
     */
    private static MessageDispatcher newServerMessageDispatcher(){
        MessageDispatcher messageDispatcher =new MessageDispatcher();

        messageDispatcher.registerHandler(PCSMessage.client_server_ping.class, (channel, message) -> {
            System.out.println("rcv ping message.");
            channel.writeAndFlush(PCSMessage.server_client_pong.newBuilder().build());
        });

        messageDispatcher.registerHandler(PCSMessage.server_client_one_request.class, (channel, message) -> {
            // 同样的原因,不使用toString()是为了展示注册的消息，在回调时不必再强转
            System.out.println("param="+message.getParam());
//            System.out.println(message.toString());
            PCSMessage.server_client_one_request_result requestResult = PCSMessage.server_client_one_request_result.newBuilder()
                    .setParam(message.getParam())
                    .setResult("success").build();
            channel.writeAndFlush(requestResult);
        });

        return messageDispatcher;
    }
}
