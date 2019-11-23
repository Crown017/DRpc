package com.drpc.client;


import com.crown.servicecommon.protocal.DRpcReponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DRpcClientHandler extends ChannelInboundHandlerAdapter {

    private DRpcReponse<String> dRpcReponse;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接受到相应----------");


        System.out.println("msg : " + msg);
        if (msg instanceof  DRpcReponse){
            DRpcReponse<String> dRpcReponse = (DRpcReponse<String>) msg;
            this.dRpcReponse = dRpcReponse;
        }
        ctx.close();
    }


    public DRpcReponse<String> getdRpcReponse() {
        return dRpcReponse;
    }

    public void setdRpcReponse(DRpcReponse<String> dRpcReponse) {
        this.dRpcReponse = dRpcReponse;
    }
}
