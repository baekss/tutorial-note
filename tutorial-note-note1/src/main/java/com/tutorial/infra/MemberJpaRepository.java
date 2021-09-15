package com.tutorial.infra;

import com.tutorial.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class MemberJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList().stream()
                .map(member -> member.of())
                .collect(toList());
    }
}
