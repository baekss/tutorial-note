package com.tutorial.application;

import com.tutorial.domain.Grade;
import com.tutorial.domain.Member;
import com.tutorial.domain.Picture;
import com.tutorial.domain.members.BlackMember;
import com.tutorial.domain.members.VipMember;
import com.tutorial.domain.members.WhiteMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    private Member peter;

    @BeforeEach
    void setUp() {
        Member 마크 = new Member("마크");
        마크.addPicture(new Picture("마크사진_1", true));
        마크.addPicture(new Picture("마크사진_2", true));
        마크.addPicture(new Picture("마크사진_3", true));
        마크.changeGrade(Grade.VIP);

        Member 피터 = new Member("피터");
        피터.addPicture(new Picture("피터사진_1", false));
        피터.addPicture(new Picture("피터사진_2", false));
        피터.addPicture(new Picture("피터사진_3", true));
        피터.changeGrade(Grade.BLACK);

        Member 사이먼 = new Member("사이먼");
        사이먼.addPicture(new Picture("사이먼_1", false));
        사이먼.addPicture(new Picture("사이먼_2", false));
        사이먼.addPicture(new Picture("사이먼_3", false));
        사이먼.changeGrade(Grade.WHITE);

        memberService.save(마크);
        peter = memberService.save(피터);
        memberService.save(사이먼);
    }

    @Test
    @DisplayName("일시적인 사진 리스트 삭제")
    public void removePictureTemporarily() {
        List<Picture> pictures =  memberService.removePictureTemporarily(peter.getId());
        assertThat(pictures).hasSize(1);
    }

    @Test
    @DisplayName("멤버 정보 가져오기")
    public void findMemberById() {
        Member member = memberService.findMemberById(peter.getId());
        assertThat(member.getName()).isEqualTo("피터");
    }

    @Test
    @DisplayName("멤버 리스트 가져오기")
    public void findMembers() {
        List<Member> members = memberService.findMembers();
        Map<String, LinkedList<Member>> map = members.stream()
                .collect(groupingBy(Member::getName, toCollection(LinkedList::new)));

        assertThat(map.get("마크").getFirst() instanceof VipMember).isTrue();
        assertThat(map.get("피터").getFirst() instanceof BlackMember).isTrue();
        assertThat(map.get("사이먼").getFirst() instanceof WhiteMember).isTrue();
    }
}
