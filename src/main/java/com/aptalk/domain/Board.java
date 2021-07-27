package com.aptalk.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Board extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;

    @Column(nullable = false, unique = true)
    public String title;

    public String content;


}

