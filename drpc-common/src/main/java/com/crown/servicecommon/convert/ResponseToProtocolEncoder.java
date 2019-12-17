package com.crown.servicecommon.convert;

import com.crown.servicecommon.codec.ProtocolConstant;
import com.crown.servicecommon.protocol.DRpcProtocol;
import com.crown.servicecommon.protocol.DRpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;


/**
 * 服务端讲响应数据转化成协议
 *
 */
public class ResponseToProtocolEncoder extends MessageToMessageEncoder<DRpcResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx, DRpcResponse msg, List<Object> out) throws Exception {



        Object data = msg.getData();
        String json = FastJsonUtil.bean2Json(data);

        int length = json.length();
        byte[] content = json.getBytes();


        DRpcProtocol protocol = new DRpcProtocol();
        protocol.setMagic(ProtocolConstant.MAGIC_NUM);
        protocol.setHeartbeat((byte) 1);
        protocol.setmType(ProtocolConstant.REQUEST);
        protocol.setStatus(msg.getStatus());

        protocol.setLength(length);
        protocol.setContent(content);
    }
}
