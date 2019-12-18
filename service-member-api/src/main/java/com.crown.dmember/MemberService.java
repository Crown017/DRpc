package com.crown.dmember;

import com.crown.servicecommon.domain.UserInfo;

public interface MemberService {
    UserInfo getUser(int id);
}
