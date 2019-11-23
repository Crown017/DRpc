package com.crown.servicecommon.LoadBalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//负载均衡
public class LoopLoadBanlance implements LoadBanlance {

    private List<String> serviceList;

    private static AtomicInteger index = new AtomicInteger(0);

    public LoopLoadBanlance(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public String selectAddress(String serviceName) {
        if (index.intValue() >= serviceList.size()){
            index.set(0);
        }
        return serviceList.get(index.getAndIncrement());
    }
}
