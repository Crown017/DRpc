package com.drpc.client;


import com.crown.servicecommon.protocal.DRpcReponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DRpcClientHandler extends ChannelInboundHandlerAdapter {

    private DRpcReponse<String> dRpcReponse;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof  DRpcReponse){
            DRpcReponse<String> dRpcReponse = (DRpcReponse<String>) msg;
            System.out.println("客户端接受到相应");
            this.dRpcReponse = dRpcReponse ;
        }
    }


    public DRpcReponse<String> getdRpcReponse() {
        return dRpcReponse;
    }

    public void setdRpcReponse(DRpcReponse<String> dRpcReponse) {
        this.dRpcReponse = dRpcReponse;
    }
}
