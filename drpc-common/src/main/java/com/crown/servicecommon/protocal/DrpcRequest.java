package com.crown.servicecommon.protocal;

import java.io.Serializable;

public class DrpcRequest  implements Serializable {

    private static final  long serialVersionUID = 1L;


    private String serviceName;
    private String methodName;
    private Class<?>[] parameterTypes;
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
