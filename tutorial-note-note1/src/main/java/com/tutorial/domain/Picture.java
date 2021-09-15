package com.tutorial.domain;

import javax.persistence.*;

@Entity
public class Picture {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private boolean usable;

    @ManyToOne
    @JoinColumn
    private Member member;

    protected Picture() { }

    public Picture(String title, boolean usable) {
        this.title = title;
        this.usable = usable;
    }

    public String getTitle() {
        return title;
    }

    public boolean isUsable() {
        return usable;
    }

    public void linkMember(Member member) {
        this.member = member;
    }
}
