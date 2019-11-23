package com.crown.serverice.impl;

import com.crown.dmember.MemberService;
import com.crown.servicecommon.annotaion.DRpcService;

@DRpcService(MemberService.class)
public class MemberServiceImpl implements MemberService {
    public String getUser(Long id) {
        return "dinosaur";
    }
}
