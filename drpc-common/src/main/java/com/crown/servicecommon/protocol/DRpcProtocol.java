package com.crown.servicecommon.protocol;

import java.util.Arrays;

/**
 * 自定义协议之DRpcProtocol
 *
 *
 * @author crown
 */
public class DRpcProtocol {


    //魔数 标识协议
    private short magic;
    //是否是心跳包
    private byte heartbeat;
    //request,response
    private byte mType;
    //标示Request和Response的状态
    private int status;
    //数据长度
    private int length;
    //请求体的数据
    private byte[] content;


    public short getMagic() {
        return magic;
    }

    public void setMagic(short magic) {
        this.magic = magic;
    }

    public byte getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(byte heartbeat) {
        this.heartbeat = heartbeat;
    }

    public byte getmType() {
        return mType;
    }

    public void setmType(byte mType) {
        this.mType = mType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public DRpcProtocol(short magic, byte heartbeat, byte mType, int status, int length, byte[] content) {
        this.magic = magic;
        this.heartbeat = heartbeat;
        this.mType = mType;
        this.status = status;
        this.length = length;
        this.content = content;
    }

    public DRpcProtocol() {
    }

    @Override
    public String toString() {
        return "DRpcProtocol{" +
                "magic=" + magic +
                ", heartbeat=" + heartbeat +
                ", mType=" + mType +
                ", status=" + status +
                ", length=" + length +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
