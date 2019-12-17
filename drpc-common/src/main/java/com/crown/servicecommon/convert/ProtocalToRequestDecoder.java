package com.crown.servicecommon.convert;

import com.crown.servicecommon.protocol.DRpcProtocol;
import com.crown.servicecommon.protocol.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.util.List;

public class ProtocalToRequestDecoder  extends MessageToMessageDecoder<DRpcProtocol> {

    private Logger logger = LogManager.getLogger(ProtocalToRequestDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, DRpcProtocol msg, List<Object> out) throws Exception {

        logger.info("把协议转化成DRpcRequest");

        byte[] content = msg.getContent();

        String json = new String(content, Charset.forName("utf-8"));

        DrpcRequest drpcRequest = FastJsonUtil.json2Bean(json,DrpcRequest.class);

        out.add(drpcRequest);
    }
}
