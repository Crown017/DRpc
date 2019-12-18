package com.crown.servicecommon.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crown.servicecommon.domain.UserInfo;
import com.crown.servicecommon.protocol.DRpcProtocol;
import com.crown.servicecommon.protocol.DRpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class ProtocolToResponseDecoder extends MessageToMessageDecoder<DRpcProtocol> {
    @Override
    protected void decode(ChannelHandlerContext ctx, DRpcProtocol msg, List<Object> out) throws Exception {
        byte[] content = msg.getContent();

        String json = new String(content, Charset.forName("utf-8"));

        DRpcResponse dRpcResponse = FastJsonUtil.json2Bean(json, DRpcResponse.class);

        JSONObject data = (JSONObject) dRpcResponse.getData();
        Class<?> aClass = Class.forName(dRpcResponse.getResultTypeName());

        Object object = data.toJavaObject(aClass);

        dRpcResponse.setData(object);

        out.add(dRpcResponse);

    }
}
