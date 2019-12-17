package com.crown.servicecommon.codec;

/**
 * 协议的常量
 */
public interface ProtocolConstant {
    public static final short MAGIC_NUM = 13;
    public static final int P_STATUS_OK = 200;
    public static final int P_STATUS_ERROR = 400;
    public static final byte REQUEST = 6;
    public static final byte RESPONSE = 9;
}
