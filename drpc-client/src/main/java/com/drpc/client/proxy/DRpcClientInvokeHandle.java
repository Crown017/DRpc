package com.drpc.client.proxy;

import com.crown.servicecommon.discover.DRpcDiscover;
import com.crown.servicecommon.discover.DRpcDiscoverImpl;
import com.crown.servicecommon.protocal.DrpcRequest;
import com.drpc.client.DrpcClient;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class DRpcClientInvokeHandle<T> implements InvocationHandler {

    private Class<T> interfaces;

    public DRpcClientInvokeHandle() {
    }

    public DRpcClientInvokeHandle(Class<T> interfaces) {
        this.interfaces = interfaces;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DRpcDiscover discover = new DRpcDiscoverImpl();
        String serviceName = interfaces.getName();
        String servicePath  = discover.serviceDiscover(serviceName);
        if (StringUtils.isEmpty(servicePath)){
            throw new Exception("no service find exception");
        }
        String[] paths = servicePath.split(":");
        String host = paths[1].replaceAll("//","");
        String port = paths[2];
        DrpcRequest drpcRequest = new DrpcRequest(serviceName,method.getName(),method.getParameterTypes(),args);
        DrpcClient drpcClient = new DrpcClient(drpcRequest);
        drpcClient.startNetty(host,Integer.parseInt(port));
        return drpcClient.getdRpcReponse().getData();
    }
}