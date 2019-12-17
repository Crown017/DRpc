package com.crown.servicecommon.convert;

import com.crown.servicecommon.codec.ProtocolConstant;
import com.crown.servicecommon.protocol.DRpcProtocol;
import com.crown.servicecommon.protocol.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class RequestToProtocolEnCoder extends MessageToMessageEncoder<DrpcRequest> {

    private  static Logger logger = LogManager.getLogger(RequestToProtocolEnCoder.class);


    @Override
    protected void encode(ChannelHandlerContext ctx, DrpcRequest msg, List<Object> out) throws Exception {
        logger.info("把编码请求编码成一个DRpcProtocol");
        String json = FastJsonUtil.bean2Json(msg);
        int length = json.length();
        byte[] content = json.getBytes();

        DRpcProtocol protocol = new DRpcProtocol();
        protocol.setMagic(ProtocolConstant.MAGIC_NUM);
        protocol.setHeartbeat((byte) 1);
        protocol.setmType(ProtocolConstant.REQUEST);
        protocol.setStatus(ProtocolConstant.P_STATUS_OK);
        protocol.setLength(length);
        protocol.setContent(content);

        out.add(protocol);

    }
}
