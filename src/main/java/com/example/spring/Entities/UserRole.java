package com.example.spring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "role")
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    Integer goldStar;

    Integer silverStar;

    Integer bronzeStar;

    Integer priority;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "roles")
    Set<User> users = new HashSet<>();

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goldStar=" + goldStar +
                ", silverStar=" + silverStar +
                ", bronzeStar=" + bronzeStar +
                ", priority=" + priority +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
