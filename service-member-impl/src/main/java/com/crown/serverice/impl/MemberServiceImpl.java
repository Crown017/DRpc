package com.crown.serverice.impl;

import com.crown.dmember.MemberService;
import com.crown.servicecommon.annotaion.DRpcService;
import com.crown.servicecommon.domain.UserInfo;

import java.util.UUID;

@DRpcService(MemberService.class)
public class MemberServiceImpl implements MemberService {
    public UserInfo getUser(Long id) {
        String token = UUID.randomUUID().toString();
        UserInfo userInfo = new UserInfo(id,"张三",token);
        return userInfo;
    }
}
