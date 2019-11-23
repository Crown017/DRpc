package com.crown.order.impl;

import com.crown.dmember.MemberService;
import com.drpc.client.proxy.DRpcClentProxy;

public class OrderServiceImpl {
    public static void main(String[] args) {
        MemberService memberService = DRpcClentProxy.create(MemberService.class);
        String string  =  memberService.getUser(1L);
        System.out.println(string);
    }
}
