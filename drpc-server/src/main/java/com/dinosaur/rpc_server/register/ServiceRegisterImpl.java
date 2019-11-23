package com.dinosaur.rpc_server.register;



import com.crown.servicecommon.Constant;
import org.I0Itec.zkclient.ZkClient;

import java.net.URLEncoder;

public class ServiceRegisterImpl implements DRpcServiceRegister {

    private ZkClient zkClient = null;




    public void register(String serviceName, String serviceAddress,String serviceType) {
        if (zkClient == null){
            zkClient = connectZk();
        }

        //创建根路径
        if (!zkClient.exists(Constant.root_path)){
            zkClient.createPersistent(Constant.root_path);
        }
        //创建接口路径
        String serviceNamePath = Constant.root_path + "/" + serviceName;
        if (!zkClient.exists(serviceNamePath)){
            zkClient.createPersistent(serviceNamePath);
        }
        //创建服务地址目录
        String servicePath =  serviceNamePath +"/" + serviceType;
        if (!zkClient.exists(servicePath)){
            zkClient.createPersistent(servicePath);
        }
        String nodePath = servicePath +"/"+ URLEncoder.encode(serviceAddress) ;
        //创建我们的服务地址
        if (zkClient.exists(nodePath)){
            zkClient.delete(nodePath);
        }
        zkClient.createEphemeral(nodePath);
    }


    private  ZkClient connectZk(){
        if (zkClient == null){
            this.zkClient = new ZkClient(Constant.zkServer,Constant.ZK_TIMEOUT);
        }
        return this.zkClient;
    }



}
