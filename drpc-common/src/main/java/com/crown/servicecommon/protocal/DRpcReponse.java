package com.crown.servicecommon.protocal;

import java.io.Serializable;

/**
 * 响应对象
 *
 * @param <T> 响应体泛型
 */
public class DRpcReponse<T> implements Serializable {
    //状态码
    private Integer status;
    //数据类型 如果没有实现Serializable请实现Serializable接口
    private T data;

    public DRpcReponse(Integer status, T data) {
        this.status = status;
        this.data = data;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
