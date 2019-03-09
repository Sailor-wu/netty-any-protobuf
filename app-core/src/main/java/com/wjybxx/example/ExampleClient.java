package com.wjybxx.example;

import com.wjybxx.generatedmessage.PCSMessage;
import com.wjybxx.logger.LoggerUtils;
import com.wjybxx.protobuf.MessageDispatcher;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

/**
 * 用于展示的客户端
 */
public class ExampleClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        LoggerUtils.createLogFile("ExampleClient.log");

        EventLoopGroup workGroup=new NioEventLoopGroup(1);
        int serverPort=12345;
        try {
            Bootstrap bootstrap=new Bootstrap();

            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(newClientInitializer())
                    .option(ChannelOption.SO_SNDBUF,8192)
                    .option(ChannelOption.SO_RCVBUF,8192)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_KEEPALIVE,false);

            ChannelFuture future = bootstrap.connect("localhost",serverPort).sync();
            timedSendMsg(future.channel());
            future.channel().closeFuture().sync();
        }finally {
            workGroup.shutdownGracefully();
        }
    }

    private static ChannelInitializer<SocketChannel> newClientInitializer(){
        return new ExampleClientInitializer(newClientMessageDispatcher());
    }

    /**
     * 注册客户端要处理的消息
     * @return
     */
    private static MessageDispatcher newClientMessageDispatcher(){
        MessageDispatcher messageDispatcher =new MessageDispatcher();

        // 服务器发来的第一条消息
        messageDispatcher.registerHandler(PCSMessage.server_client_first_message.class, (channel, message) -> {
            System.out.println("name="+message.getName() +", uid="+message.getUid());
            // 不使用toString()是为了展示注册的消息，在回调时不必再强转
//            System.out.println(message.toString());
        });

        // pong包消息
        messageDispatcher.registerHandler(PCSMessage.server_client_pong.class, (channel, message) -> {
            System.out.println("rcv server pong.");
        });

        // request返回消息
        messageDispatcher.registerHandler(PCSMessage.server_client_one_request_result.class, (channel, message) -> {
            // toString() 也是可读性很好的
            System.out.println(message.toString());
        });
        return messageDispatcher;
    }

    /**
     * 定时发送消息
     * @param channel
     */
    private static void timedSendMsg(Channel channel){
        for (int index=0;index<100;index++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            }

            // 每10次一个ping包
            if (index%10==0){
                PCSMessage.client_server_ping pingMsg = PCSMessage.client_server_ping.newBuilder().build();
                channel.writeAndFlush(pingMsg);
            }

            // 每5次发一个请求
            if (index%5==0){
                PCSMessage.server_client_one_request msg= PCSMessage.server_client_one_request.newBuilder()
                        .setParam(index).build();
                channel.writeAndFlush(msg);
            }
        }
    }
}
