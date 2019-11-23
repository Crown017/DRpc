package com.dinosaur.rpc_server.server;

import com.crown.servicecommon.annotaion.DRpcService;
import com.crown.servicecommon.Constant;
import com.dinosaur.rpc_server.register.DRpcServiceRegister;
import com.dinosaur.rpc_server.register.ServiceRegisterImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class DRpcServer {

    //Netty的配置
    private static String host = "127.0.0.1";
    private static Integer nport = 8899;
    private static ConcurrentHashMap handleMap = new ConcurrentHashMap();

    public void start(){
//        bind();
        nettyServerStartUp();
    }

    //服务的注册
    public void bind(Object service) throws  Exception{
        if (service == null){
            throw new Exception("服务为null");
        }
        DRpcService  dRpcService = service.getClass().getAnnotation(DRpcService.class);
        if (dRpcService == null){
            throw new Exception("No annotation of:" + DRpcService.class.getName());
        }
        String serviceName = dRpcService.value().getName();
        DRpcServiceRegister dRpcServiceRegister = new  ServiceRegisterImpl();
        String address = "dinosaur" + "//" + host+":"+nport;
        dRpcServiceRegister.register(serviceName,address, Constant.serviceType);
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
