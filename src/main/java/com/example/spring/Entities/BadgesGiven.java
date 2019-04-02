package com.example.spring.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class BadgesGiven {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    User giver;

    @ManyToOne
    User receiver;

    String comment;

    @OneToOne
    Star star;

    boolean flag;

    @Column(name = "createDateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "updateDateTime")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
