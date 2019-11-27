package com.findme_spring_boot.model.h2;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TEXT")
@Getter
@Setter
@ToString
public class Text {

    @Id
    @SequenceGenerator(name = "TEXT_SEQ", sequenceName = "TEXT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEXT_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;

    public Text() {
    }
}

