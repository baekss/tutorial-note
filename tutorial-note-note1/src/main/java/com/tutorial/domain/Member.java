package com.tutorial.domain;

import com.tutorial.domain.members.BlackMember;
import com.tutorial.domain.members.VipMember;
import com.tutorial.domain.members.WhiteMember;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int point;
    private int money;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Picture> pictures = new ArrayList<>();

    protected Member() { }

    public Member(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public int getMoney() {
        return money;
    }

    public Grade getGrade() {
        return grade;
    }

    public void changeGrade(Grade grade) {
        this.grade = grade;
    }

    public void addPicture(Picture picture) {
        pictures.add(picture);
        picture.linkMember(this);
    }

    public List<Picture> removePicture() {
        return pictures.stream()
                .filter(p -> p.isUsable())
                .collect(toList());
    }

    public int getRefundForResignation() {
        return -1;
    }

    private boolean isVip() {
        return grade == Grade.VIP;
    }

    private boolean isBlack() {
        return grade == Grade.BLACK;
    }

    private boolean isWhite() {
        return grade == Grade.WHITE;
    }

    protected Member(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.grade = member.getGrade();
    }

    public Member of() {
        if (isVip()) {
            return new VipMember(this);
        } else if (isBlack()) {
            return new BlackMember(this);
        }
        return new WhiteMember(this);
    }
}
