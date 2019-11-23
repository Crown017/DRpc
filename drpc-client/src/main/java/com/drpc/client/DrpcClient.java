package com.drpc.client;

import com.crown.servicecommon.protocal.DRpcReponse;
import com.crown.servicecommon.protocal.DrpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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
    public void startNetty(String host,Integer port) throws Exception{
        DRpcInitiaLizer initiaLizer = new DRpcInitiaLizer();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(initiaLizer);
            ChannelFuture channelFuture = bootstrap.connect().sync();
            channelFuture.channel().writeAndFlush(drpcRequest);
            this.dRpcReponse = initiaLizer.getdRpcReponse();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
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
