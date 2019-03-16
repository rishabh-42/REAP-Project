package com.example.spring.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BadgeAssignment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "senderId")
    int senderId;

    @Column(name = "recieverId")
    int recieverId;


    @Column(name = "starId")
    int starId;

    @Column(name = "comment")
    String comment;

    @Column(name = "value")
    String value;

    @Column(name = "createDateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "updateDateTime")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    //Mapping

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "starId")
    private Stars stars;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recieverId")
    private User reciever;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "value")
    private Values values;

}
