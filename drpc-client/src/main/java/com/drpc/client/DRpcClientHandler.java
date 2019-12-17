package com.drpc.client;


import com.crown.servicecommon.protocol.DRpcResponse;
import com.crown.servicecommon.protocol.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class DRpcClientHandler extends SimpleChannelInboundHandler<DRpcResponse> {

    private DRpcResponse dRpcResponse;
    private DrpcRequest drpcRequest;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DRpcResponse msg) throws Exception {
        System.out.println("客户端接受到相应----------");
        System.out.println("msg : " + msg);

        this.dRpcResponse = dRpcResponse;
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
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
