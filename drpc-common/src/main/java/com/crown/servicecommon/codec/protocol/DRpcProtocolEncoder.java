package com.crown.servicecommon.codec.protocol;

import com.crown.servicecommon.protocol.DRpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class DRpcProtocolEncoder extends MessageToByteEncoder<DRpcProtocol> {

    private static Logger logger = LogManager.getLogger(DRpcProtocolEncoder.class);


    @Override
    protected void encode(ChannelHandlerContext ctx, DRpcProtocol msg, ByteBuf out) throws Exception {
        logger.info("服务端编码：把协议写到ByteBuf里面");
        out.writeShort(msg.getMagic());
        out.writeByte(msg.getHeartbeat());
        out.writeByte(msg.getmType());
        out.writeInt(msg.getStatus());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
