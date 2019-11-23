package com.drpc.client;

import com.crown.servicecommon.protocal.DRpcReponse;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class DRpcInitiaLizer extends ChannelInitializer<SocketChannel> {

    private DRpcReponse<String> dRpcReponse;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
       ChannelPipeline channelPipeline =  socketChannel.pipeline();
       DRpcClientHandler handler =  new DRpcClientHandler();
       channelPipeline.addLast(handler);
       this.dRpcReponse =  handler.getdRpcReponse();
    }

    public DRpcReponse<String> getdRpcReponse() {
        return dRpcReponse;
    }

    public void setdRpcReponse(DRpcReponse<String> dRpcReponse) {
        this.dRpcReponse = dRpcReponse;
    }
}
