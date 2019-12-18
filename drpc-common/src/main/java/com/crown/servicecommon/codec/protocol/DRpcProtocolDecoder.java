package com.crown.servicecommon.codec.protocol;

import com.crown.servicecommon.codec.ProtocolConstant;
import com.crown.servicecommon.protocol.DRpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * 自定义协议的解码器
 *
 * 入站信息ByteBuf 转换成我们需要的对象
 */
public class DRpcProtocolDecoder extends ReplayingDecoder<Void> {

    private static final Logger logger = LogManager.getLogger(DRpcProtocolDecoder.class.getName());


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


        logger.info("客户端接受到请求要转化成DRpcProtocol");

        short magic =  in.readShort();
        byte heartbeat = in.readByte();
        byte mType = in.readByte();
        int status  = in.readInt();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);


        //是我们的DRpc协议
        if (magic == ProtocolConstant.MAGIC_NUM){
            DRpcProtocol protocol = new DRpcProtocol(magic,heartbeat,mType,status,length,bytes);
            out.add(protocol);
        }
    }
}
