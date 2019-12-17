package com.crown.servicecommon.convert;

import com.crown.servicecommon.codec.ProtocolConstant;
import com.crown.servicecommon.protocol.DRpcProtocol;
import com.crown.servicecommon.protocol.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;


public class RequestToProtocalEnCode extends MessageToMessageEncoder<DrpcRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, DrpcRequest msg, List<Object> out) throws Exception {
        String json = FastJsonUtil.bean2Json(msg);
        int length = json.length();
        byte[] content = json.getBytes();

        DRpcProtocol protocol = new DRpcProtocol();
        protocol.setMagic(ProtocolConstant.MAGIC_NUM);
        protocol.setHeartbeat((byte) 1);
        protocol.setmType(ProtocolConstant.REQUEST);
        protocol.setLength(length);
        protocol.setContent(content);


        out.add(protocol);

    }
}
