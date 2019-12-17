package com.drpc.client;


import com.crown.servicecommon.protocol.DRpcResponse;
import com.crown.servicecommon.protocol.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class DRpcClientHandler extends SimpleChannelInboundHandler<DRpcResponse> {

    private  static Logger logger = LogManager.getLogger(DRpcClientHandler.class);

    private DRpcResponse dRpcResponse;
    private DrpcRequest drpcRequest;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DRpcResponse dRpcResponse) throws Exception {
        logger.info("客户端接受到相应----------");
        logger.info("msg : " + dRpcResponse);

        this.dRpcResponse = dRpcResponse;
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端发送请求"+ drpcRequest.getServiceName() );
        ctx.writeAndFlush(drpcRequest);
    }

    public DRpcResponse getdRpcResponse() {
        return dRpcResponse;
    }

    public void setdRpcResponse(DRpcResponse dRpcResponse) {
        this.dRpcResponse = dRpcResponse;
    }

    public DrpcRequest getDrpcRequest() {
        return drpcRequest;
    }

    public void setDrpcRequest(DrpcRequest drpcRequest) {
        this.drpcRequest = drpcRequest;
    }
}
