package com.example.spring.Entities;



import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "star")
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer weight;


    @Column(name = "createDateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "updateDateTime")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
