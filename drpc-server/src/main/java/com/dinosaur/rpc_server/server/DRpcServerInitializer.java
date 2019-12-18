package com.dinosaur.rpc_server.server;

import com.crown.servicecommon.decoder.MarshallingCodeCFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author clown
 *
 * 服务端初始化器
 */
public class DRpcServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
       ChannelPipeline pipeline =  socketChannel.pipeline();
        /**
         * 添加基于XML的编解码器
         */
       pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
       pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());

        /**
         * 业务处理的Handler
         */
        pipeline.addLast(new DRpcServerReqHandler());
    }
}
