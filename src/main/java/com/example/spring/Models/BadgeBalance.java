package com.example.spring.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BadgeBalance {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "numGold")
    int numGold;

    @Column(name = "numSilver")
    int numSilver;

    @Column(name = "numBronze")
    int numBronze;

    @Column(name = "createDateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "updateDateTime")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    @OneToOne
    @PrimaryKeyJoinColumn
    User user;


}
