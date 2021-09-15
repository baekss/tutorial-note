package com.tutorial.application;

import com.tutorial.domain.Member;
import com.tutorial.infra.MemberJpaRepository;
import com.tutorial.domain.MemberRepository;
import com.tutorial.domain.Picture;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberJpaRepository memberJpaRepository;

    public MemberService(final MemberRepository memberRepository, final MemberJpaRepository memberJpaRepository) {
        this.memberRepository = memberRepository;
        this.memberJpaRepository = memberJpaRepository;
    }

    public Member save(Member member) {
        // synchronized의 효과는 동일 was(JVM)에서 유효함
        // ex. 10대의 was에서 완전 동일한 타임에 호출시 인원 체크 보장 안됨
        // commit은 MemberService의 Proxy 객체에서 발생함
        // read committed를 고려하여 checkMemberCount 결과 예측
        synchronized (this) {
            if (checkMemberCount()) {
                throw new RuntimeException("인원 초과");
            }
            return memberRepository.save(member);
        }
    }

    public List<Picture> removePictureTemporarily(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return member.removePicture();
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findMemberById(id);
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberJpaRepository.findAll();
    }

    private boolean checkMemberCount() {
        return memberRepository.count() > 100;
    }
}
