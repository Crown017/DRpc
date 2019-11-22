package com.dinosaur.rpc_server.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class DRpcServer {

    //Netty的配置
    private static String host = "127.0.0.1";
    private static Integer nport = 8899;


    //服务的注册
    public void bind(String serviceName,String address){

    }


    //
    public void nettyServerStartUp(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nport))
                .childHandler(new DRpcServerInitlializer());

        try {
            serverBootstrap.bind().sync();
        }catch (Exception e){

        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
