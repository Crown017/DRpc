package com.crown.servicecommon.LoadBalance;


import java.util.List;
import java.util.Random;

/**
 * 随机负载均衡
 */
public class RandomLoadBanlance implements LoadBanlance{

    private List<String> serviceList;

    public RandomLoadBanlance(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public RandomLoadBanlance() {
    }

    public String selectAddress(String serviceName) {
        Random random = new Random();
        return serviceList.get(random.nextInt(serviceList.size()));
    }
}
