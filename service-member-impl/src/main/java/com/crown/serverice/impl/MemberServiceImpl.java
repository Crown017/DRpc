package com.crown.serverice.impl;

import com.crown.dmember.MemberService;
import com.crown.servicecommon.annotaion.DRpcService;
import com.crown.servicecommon.domain.UserInfo;

import java.util.UUID;

@DRpcService(MemberService.class)
public class MemberServiceImpl implements MemberService {
    public UserInfo getUser(int id) {
        String token = UUID.randomUUID().toString();
        UserInfo userInfo = new UserInfo(id,"zhangsan",token);
        return userInfo;
    }
}
