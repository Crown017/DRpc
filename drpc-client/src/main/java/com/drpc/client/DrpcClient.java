package com.drpc.client;

import com.crown.servicecommon.decoder.MarshallingCodeCFactory;
import com.crown.servicecommon.protocal.DRpcReponse;
import com.crown.servicecommon.protocal.DrpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class DrpcClient {

    private DrpcRequest drpcRequest;

    private DRpcReponse dRpcReponse;

    public DrpcClient(DrpcRequest drpcRequest) {
        this.drpcRequest = drpcRequest;
    }

    /**
     * 连接到服务起端
     * @param host
     * @param port
     */
    public Object startNetty(String host,Integer port) throws Exception{
        Object dRpcReponse =  null;
        DRpcClientHandler handler =  new DRpcClientHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline =  socketChannel.pipeline();

                            channelPipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            channelPipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            channelPipeline.addLast(handler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().writeAndFlush(drpcRequest);
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
        return handler.getdRpcReponse().getData();
    }


    public DrpcRequest getDrpcRequest() {
        return drpcRequest;
    }

    public void setDrpcRequest(DrpcRequest drpcRequest) {
        this.drpcRequest = drpcRequest;
    }

    public DRpcReponse getdRpcReponse() {
        return dRpcReponse;
    }

    public void setdRpcReponse(DRpcReponse dRpcReponse) {
        this.dRpcReponse = dRpcReponse;
    }
}
