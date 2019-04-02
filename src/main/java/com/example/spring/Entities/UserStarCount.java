package com.example.spring.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class UserStarCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    @OneToOne(fetch = FetchType.EAGER)
    User user;

    Integer goldStarCount;

    Integer silverStarCount;

    Integer bronzeStarCount;

}
