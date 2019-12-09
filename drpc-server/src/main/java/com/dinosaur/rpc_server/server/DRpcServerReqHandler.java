package com.dinosaur.rpc_server.server;


import com.crown.servicecommon.protocal.DRpcReponse;
import com.crown.servicecommon.protocal.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class DRpcServerReqHandler extends ChannelInboundHandlerAdapter {

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
        System.out.println("开始接受客户端的请求-------------------");
        Object object= null;
        DrpcRequest drpcRequest = null;
        if (msg instanceof DrpcRequest){
             drpcRequest = (DrpcRequest)msg;
        }
        /**
         * 根据传输的信息通过反射调用
         */
        String serviceName = drpcRequest.getServiceName();
        String methodName = drpcRequest.getMethodName();
        Object classType = DRpcServer.handleMap.get(serviceName);
        Method method =  classType.getClass().getMethod(methodName,drpcRequest.getParameterTypes());
        object = method.invoke(classType,drpcRequest.getParamsValue());
        DRpcReponse<String>  dRpcReponse = new DRpcReponse<>(1,object.toString());
        ctx.writeAndFlush(dRpcReponse);
    }
}
