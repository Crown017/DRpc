package com.dinosaur.rpc_server.server;

import com.crown.servicecommon.annotaion.DRpcService;
import com.crown.servicecommon.Constant;
import com.dinosaur.rpc_server.register.DRpcServiceRegister;
import com.dinosaur.rpc_server.register.ServiceRegisterImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class DRpcServer {

    //Netty的配置
    private final static String host = "127.0.0.1";
    private final static Integer nport = 8899;

    /**
     * Handle本地容器缓存
     */
    public static ConcurrentHashMap handleMap = new ConcurrentHashMap();

    public void publish(Object service){
        try {
            bind(service);
            nettyServerStartUp();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 服务的注册
     *
     * 注册一个服务到注册中心上去，并在本地创建缓存。
     */
    public void bind(Object service) throws  Exception{
        if (service == null){
            throw new Exception("服务为null");
        }
        DRpcService  dRpcService = service.getClass().getDeclaredAnnotation(DRpcService.class);
        if (dRpcService == null){
            throw new Exception("No annotation of:" + DRpcService.class.getName());
        }

        String serviceName = dRpcService.value().getName();
        handleMap.put(serviceName,service);
        DRpcServiceRegister dRpcServiceRegister = new  ServiceRegisterImpl();
        String address = "dinosaur:" + "//" + host+":"+nport;
        dRpcServiceRegister.register(serviceName,address, Constant.serviceType);
    }

    /**
     * Netty 服务器端
     *
     * RpcServer端程序
     */
    public void nettyServerStartUp(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(4);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nport))
                .childHandler(new DRpcServerInitializer());

        try {
            ChannelFuture  channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
