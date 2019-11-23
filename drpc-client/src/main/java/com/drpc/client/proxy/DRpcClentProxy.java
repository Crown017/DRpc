package com.drpc.client.proxy;

import java.lang.reflect.Proxy;

public class DRpcClentProxy {
    public static  <T> T create(Class<T> interfaces){
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),interfaces.getClasses(),new DRpcClientInvokeHandle(interfaces));
    }
}
