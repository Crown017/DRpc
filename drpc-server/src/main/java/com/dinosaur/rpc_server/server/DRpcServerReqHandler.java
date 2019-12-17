package com.dinosaur.rpc_server.server;


import com.crown.servicecommon.codec.ProtocolConstant;
import com.crown.servicecommon.protocol.DRpcResponse;
import com.crown.servicecommon.protocol.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class DRpcServerReqHandler extends SimpleChannelInboundHandler<DrpcRequest> {

    private Logger logger = LogManager.getLogger(DRpcServerReqHandler.class);

    public DRpcServerReqHandler() {
    }

    /**
     * 处理客户端发过来的请求
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, DrpcRequest drpcRequest) throws Exception {
        logger.info("开始接受客户端的请求-------------------");

        /**
         * 根据传输的信息通过反射调用
         */
        String serviceName = drpcRequest.getServiceName();
        String methodName = drpcRequest.getMethodName();
        Object classType = DRpcServer.handleMap.get(serviceName);
        Method method =  classType.getClass().getMethod(methodName,drpcRequest.getParameterTypes());
        Object object = method.invoke(classType,drpcRequest.getParamsValue());
        DRpcResponse dRpcResponse = new DRpcResponse(ProtocolConstant.P_STATUS_OK,object);
        logger.info("服务端处理完请求:"+dRpcResponse);
        ctx.writeAndFlush(dRpcResponse);
    }
}
