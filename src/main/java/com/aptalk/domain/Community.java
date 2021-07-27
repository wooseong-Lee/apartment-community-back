package com.aptalk.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    public String name;

    public String notice;
}
