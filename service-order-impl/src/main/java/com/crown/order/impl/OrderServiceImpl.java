package com.crown.order.impl;

import com.crown.dmember.MemberService;
import com.crown.servicecommon.domain.UserInfo;
import com.drpc.client.proxy.DRpcClintProxy;

public class OrderServiceImpl {
    public static void main(String[] args) {
        MemberService memberService = DRpcClintProxy.create(MemberService.class);
        UserInfo string  =  memberService.getUser(1);
        System.out.println(string);
    }
}
