package com.dinosaur.rpc_server.server;

import com.crown.servicecommon.decoder.MarshallingCodeCFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class DRpcServerInitlializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
       ChannelPipeline pipeline =  socketChannel.pipeline();
       pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
       pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
       pipeline.addLast(new DRpcServerReqHandler());
    }
}
