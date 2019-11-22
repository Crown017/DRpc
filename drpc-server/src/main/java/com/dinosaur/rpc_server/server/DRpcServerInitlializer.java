package com.dinosaur.rpc_server.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class DRpcServerInitlializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
       ChannelPipeline pipeline =  socketChannel.pipeline();
       pipeline.addLast();
       pipeline.addLast();
       pipeline.addLast(new DRpcServerReqHandler());
    }
}
