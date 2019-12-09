package com.crown.servicecommon.protocal;

import java.io.Serializable;


/**
 * DRpc的请求
 *
 * 请求的一个抽象，实现序列化接口，Mashalling需要实现Serializable接口
 */
public class DrpcRequest  implements Serializable {

    private static final  long serialVersionUID = 6921944834434545047L;
    /**
     * 要调用的服务的名称
     *
     * 例如：com.crown.dmember.MemberService，以便从HandlerMap中获取
     */
    private String serviceName;
    /**
     * 调用的该接口的哪个方法名称
     * 提供给反射api调用
     */
    private String methodName;
    /**
     * 方法的形式参数
     */
    private Class<?>[] parameterTypes;

    /**
     * 方法的实参
     */
    private Object[] paramsValue;

    public DrpcRequest(String serviceName, String methodName, Class<?>[] parameterTypes, Object[] paramsValue) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.paramsValue = paramsValue;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public DrpcRequest(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }


    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParamsValue() {
        return paramsValue;
    }

    public void setParamsValue(Object[] paramsValue) {
        this.paramsValue = paramsValue;
    }
}
