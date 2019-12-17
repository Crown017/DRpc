package com.crown.servicecommon.codec.protocol;

import com.crown.servicecommon.protocol.DRpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class DRpcProtocolEncoder extends MessageToByteEncoder<DRpcProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, DRpcProtocol msg, ByteBuf out) throws Exception {
        out.writeShort(msg.getMagic());
        out.writeByte(msg.getHeartbeat());
        out.writeByte(msg.getmType());
        out.writeInt(msg.getStatus());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
