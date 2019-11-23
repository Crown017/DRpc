package com.dinosaur.rpc_server.server;


import com.crown.servicecommon.protocal.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.Map;

public class DRpcServerReqHandler extends ChannelInboundHandlerAdapter {


    private Map<String,Object> handleMap;

    public DRpcServerReqHandler(Map<String, Object> handleMap) {
        this.handleMap = handleMap;
    }

    public DRpcServerReqHandler() {
    }

    /**
     * 处理客户端发过来的请求
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object object= null;
        DrpcRequest drpcRequest = null;
        if (msg instanceof DrpcRequest){
             drpcRequest = (DrpcRequest)msg;
        }
        String serviceName = drpcRequest.getServiceName();
        String methodName = drpcRequest.getMethodName();
        Object classType = handleMap.get(serviceName);
        Method method =  classType.getClass().getMethod(methodName,drpcRequest.getParameterTypes());
        object = method.invoke(classType,drpcRequest.getParameterTypes());
        ctx.writeAndFlush(object);
    }
}
