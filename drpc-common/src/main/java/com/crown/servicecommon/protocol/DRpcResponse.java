package com.crown.servicecommon.protocol;

import java.io.Serializable;

/**
 * 响应对象
 *
 */
public class DRpcResponse implements Serializable {
    //状态码
    private Integer status;
    //数据类型 如果没有实现Serializable请实现Serializable接口
    private Object data;

    public DRpcResponse(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
