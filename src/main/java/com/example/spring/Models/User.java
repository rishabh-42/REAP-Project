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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    int userId;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "mobile")
    String mobile;

    @Column(name = "password")
    String password;

    @Column(name = "photo")
    String photo;

    @Column(name = "active")
    boolean active;

    @Column(name = "currentRoleId")
    String currentRoleId;

    @Column(name = "registrationCompleted")
    boolean registrationCompleted;

    @Column
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
