package com.drpc.client.proxy;

import java.lang.reflect.Proxy;

/**
 * Proxy组件
 *
 *
 * 不能直接通过接口来生成对象，必须要根据暴露的接口创建，代理类来实现调用
 */
public class DRpcClintProxy {
    public static  <T> T create(Class<T> interfaces){
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},new DRpcClientInvokeHandle(interfaces));
    }
}
