package com.dinosaur.rpc_server.register;


public interface DRpcServiceRegister {
    /**
     * 把服务注册到Zookeeper上面
     *
     *
     * 格式如下：/dinosaur
     *              /serviceName
     *                  /serviceType/
     *                      dinosaur:127.0.0.1:8899
     *        dubbo服务注册的时候，临时节点的数据比较多 为了给Admin平台进行管理，还有版本灰度的处理。
     * @param serviceName 服务的接口名称
     * @param serviceAddress IP地址+端口号
     * @param serviceType 是生产者还是消费者
     */
    void register(String serviceName,String serviceAddress,String serviceType);
}
