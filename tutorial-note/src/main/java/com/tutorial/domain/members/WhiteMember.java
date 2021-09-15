package com.tutorial.domain.members;

import com.tutorial.domain.Member;

public class WhiteMember extends Member {
    public WhiteMember(Member member) {
        super(member);
    }

    @Override
    public int getRefundForResignation() {
        return getMoney();
    }
}
