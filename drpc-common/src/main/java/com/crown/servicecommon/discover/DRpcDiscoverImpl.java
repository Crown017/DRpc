package com.crown.servicecommon.discover;

import com.crown.servicecommon.Constant;
import com.crown.servicecommon.LoadBalance.LoadBanlance;
import com.crown.servicecommon.LoadBalance.LoopLoadBanlance;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.util.StringUtils;

import java.util.List;

public class DRpcDiscoverImpl  implements DRpcDiscover{

    private ZkClient zkClient = null;


    private void connectZk(){
        if (zkClient == null){
            this.zkClient = new ZkClient(Constant.zkServer,Constant.ZK_TIMEOUT);
        }
    }



    public String serviceDiscover(String serviceName) throws  Exception{
        connectZk();

        String path = Constant.root_path +"/"+serviceName +"/" + Constant.serviceType;
        List<String> pathList = zkClient.getChildren(path);
         //负载均衡
        LoadBanlance loadBanlance = new LoopLoadBanlance(pathList);
        String servicePath  =  loadBanlance.selectAddress(serviceName);
        return servicePath;
    }
}
