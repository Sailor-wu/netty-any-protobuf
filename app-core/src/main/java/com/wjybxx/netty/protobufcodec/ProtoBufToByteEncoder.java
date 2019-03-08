package com.wjybxx.netty.protobufcodec;

import com.google.protobuf.MessageLite;
import com.wjybxx.protobuf.MessageMappingHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 将protoBuf协议编码为字节流
 *
 *  解码方式
 * pipeline.addLast(new {@link LengthFieldBasedFrameDecoder}(8192, 0, 4, 0, 4));
 * pipeline.addLast(new {@link ByteToProtoBufDecoder}());
 * ...
 *
 * 编码方式
 * pipeline.addLast("ProtoBufToByteEncoder", new {@link ProtoBufToByteEncoder}());
 * ...
 */
public class ProtoBufToByteEncoder extends MessageToByteEncoder<MessageLite>{

    /**
     * 将protoBuf协议编码为网络字节流
     * @param ctx
     * @param msg 需要编码的protoBuf协议
     * @param out 写入的字节流
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {
        //先获取消息对应的枚举编号
        int messageId = MessageMappingHolder.getMessageId(msg);

        //protoLength 表示有效内容的长度(不包括自身) 4 是messageId的长度
        int protoLength = 4 + msg.getSerializedSize();
        out.writeInt(protoLength);
        out.writeInt(messageId);
        try(ByteBufOutputStream byteBufOutputStream=new ByteBufOutputStream(out)){
            msg.writeTo(byteBufOutputStream);//msg.getSerializedSize()
        }
    }

}
