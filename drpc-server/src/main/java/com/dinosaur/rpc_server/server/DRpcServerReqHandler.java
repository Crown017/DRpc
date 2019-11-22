package com.dinosaur.rpc_server.server;


import com.dinosaur.rpc_server.protocal.DrpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DRpcServerReqHandler extends ChannelInboundHandlerAdapter {


    /**
     * 处理客户端发过来的请求
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object object= null;
        if (msg instanceof DrpcRequest){
            DrpcRequest drpcRequest = (DrpcRequest)msg;
        }
        ctx.writeAndFlush(object);
    }
}
