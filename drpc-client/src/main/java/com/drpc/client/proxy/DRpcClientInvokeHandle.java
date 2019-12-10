package com.drpc.client.proxy;

import com.crown.servicecommon.discover.DRpcDiscover;
import com.crown.servicecommon.discover.DRpcDiscoverImpl;
import com.crown.servicecommon.protocal.DrpcRequest;
import com.drpc.client.DrpcClient;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URLDecoder;


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
        //例如：dubbo://127.0.0.1:10888
        String servicePath  = URLDecoder.decode(discover.serviceDiscover(serviceName));
        if (StringUtils.isEmpty(servicePath)){
            throw new Exception("no service find exception");
        }
        String[] paths = servicePath.split(":");

        /**
         * 拿到端口号跟地址
         */
        String host = paths[1].replaceAll("//","");
        String port = paths[2];
        DrpcRequest drpcRequest = new DrpcRequest(serviceName,method.getName(),method.getParameterTypes(),args);
        DrpcClient drpcClient = new DrpcClient(drpcRequest);
        return drpcClient.startNetty(host,Integer.parseInt(port));
    }
}
