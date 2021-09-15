package com.tutorial.domain.members;

import com.tutorial.domain.Member;

public class BlackMember extends Member {
    public BlackMember(Member member) {
        super(member);
    }

    @Override
    public int getRefundForResignation() {
        return getMoney() + 1000;
    }
}
