package com.dinosaur.rpc_server.server;

import com.crown.servicecommon.codec.protocol.DRpcProtocolDecoder;
import com.crown.servicecommon.codec.protocol.DRpcProtocolEncoder;
import com.crown.servicecommon.convert.ProtocalToRequestDecoder;
import com.crown.servicecommon.convert.ResponseToProtocolEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;

/**
 * @author clown
 *
 * 服务端初始化器
 */
public class DRpcServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
       ChannelPipeline pipeline =  socketChannel.pipeline();
       pipeline.addLast(new JsonObjectDecoder());
//        /**
//         * 添加基于XML的编解码器
//         */
//       pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
//       pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());

       //入栈的时候解码为DRpc协议
       pipeline.addLast(new DRpcProtocolDecoder());
       //出站的时候转换成DRpc协议
       pipeline.addLast(new ResponseToProtocolEncoder());
       //把DRpc协议转化成Request
       pipeline.addLast(new ProtocalToRequestDecoder());
       //DRpc协议写到ByteBuf中
       pipeline.addLast(new DRpcProtocolEncoder());


        /**
         * 业务处理的Handler
         */
        pipeline.addLast(new DRpcServerReqHandler());
    }
}
