package com.wjybxx.netty.protobufcodec;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.wjybxx.protobuf.MessageMappingHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 食用方式
 *
 * 解码方式
 * pipeline.addLast(new {@link LengthFieldBasedFrameDecoder}(8192, 0, 4, 0, 4));
 * pipeline.addLast(new {@link ByteToProtoBufDecoder}());
 * ...
 *
 * 编码方式
 * pipeline.addLast("ProtoBufToByteEncoder", new {@link ProtoBufToByteEncoder}());
 * ...
 */
public class ByteToProtoBufDecoder extends SimpleChannelInboundHandler<ByteBuf>{

    /**
     * 自动释放msg
     */
    public ByteToProtoBufDecoder() {
        super(true);
    }

    /**
     * 在这里将字节流解码为对应的协议。
     * (这里收到的msg是已经经过{@link LengthFieldBasedFrameDecoder}(8192, 0, 4, 0, 4) 处理拆包、粘包处理之后，并跳过了前4个字节的数据包)
     * @param ctx
     * @param msg 一个完整的、待解码的数据包
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //消息的前4个字节已被跳过，所以这里已经没有了长度字段，只剩下内容部分

        //编码时使用了一个int标记protoBuf协议的类，那在解码时需要先取出该标记
        int messageId=msg.readInt();

        //通过索引获得该协议对应的解析器(客户端与服务器需要保持索引的一致性)
        Parser<?> parser= MessageMappingHolder.getParser(messageId);
        if (parser==null){
            throw new IllegalArgumentException("illegal messageId " + messageId);//自己决定如何处理协议无法解析的情况
        }

        try (ByteBufInputStream bufInputStream=new ByteBufInputStream(msg)){
            MessageLite messageLite= (MessageLite) parser.parseFrom(bufInputStream);
            ctx.fireChannelRead(messageLite);//将消息传递下去，或者在这里将消息发布出去
        }
    }

}
