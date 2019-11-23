package com.crown.servicecommon.protocal;

import java.io.Serializable;

public class DRpcReponse<T> implements Serializable {
    private Integer status;
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
