package com.crown.servicecommon.convert;

import com.crown.servicecommon.codec.ProtocolConstant;
import com.crown.servicecommon.protocol.DRpcProtocol;
import com.crown.servicecommon.protocol.DRpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.log4j.LogManager;

import java.nio.charset.Charset;
import java.util.List;
import org.apache.log4j.Logger;


/**
 * 服务端讲响应数据转化成协议
 *
 */
public class ResponseToProtocolEncoder extends MessageToMessageEncoder<DRpcResponse> {

    private static Logger logger = LogManager.getLogger(ResponseToProtocolEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, DRpcResponse msg, List<Object> out) throws Exception {

        logger.info("把DRpcResponse 转换成一个 DRpcProtocol");

//        Object data = msg.getData();
        String json = FastJsonUtil.bean2Json(msg);

        int length = json.length();
        byte[] content = json.getBytes(Charset.forName("utf-8"));


        DRpcProtocol protocol = new DRpcProtocol();
        protocol.setMagic(ProtocolConstant.MAGIC_NUM);
        protocol.setHeartbeat((byte) 1);
        protocol.setmType(ProtocolConstant.REQUEST);
        protocol.setStatus(msg.getStatus());

        protocol.setLength(length);
        protocol.setContent(content);

        out.add(protocol);
    }
}
