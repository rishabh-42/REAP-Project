package com.example.spring.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Stars {

    @Id
    @Column(name = "starId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int starId;

    @Column(name = "type")
    String type;

    @Column(name = "weightage")
    int weightage;

    @Column(name="createDateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "updateDateTime")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
}

