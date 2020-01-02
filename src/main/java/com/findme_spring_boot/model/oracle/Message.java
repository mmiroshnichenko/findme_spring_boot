package com.findme_spring_boot.model.oracle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MESSAGE")
@Getter
@Setter
@ToString
public class Message {
    @Id
    @SequenceGenerator(name = "MESSAGE_SEQ",sequenceName = "MESSAGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATE_SENT")
    private Date dateSent;

    @Column(name = "DATE_READ")
    private Date dateRead;

    @Column(name = "DATE_DELETED")
    private Date dateDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_FROM_ID", nullable = false)
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_TO_ID", nullable = false)
    private User userTo;
}
