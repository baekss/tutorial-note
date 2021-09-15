package com.tutorial.domain.members;

import com.tutorial.domain.Member;

public class VipMember extends Member {
    public VipMember(Member member) {
        super(member);
    }

    @Override
    public int getRefundForResignation() {
        return getPoint() + getMoney() + 2000;
    }
}
