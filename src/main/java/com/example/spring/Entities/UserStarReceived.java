package com.example.spring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class UserStarReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    User user;

    Integer goldStarRecieved;

    Integer silverStarRecieved;

    Integer bronzeStarRecieved;

    Integer points = 0;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getGoldStarRecieved() {
        return goldStarRecieved;
    }

    public void setGoldStarRecieved(Integer goldStarRecieved) {
        this.goldStarRecieved = goldStarRecieved;
    }

    public Integer getSilverStarRecieved() {
        return silverStarRecieved;
    }

    public void setSilverStarRecieved(Integer silverStarRecieved) {
        this.silverStarRecieved = silverStarRecieved;
    }

    public Integer getBronzeStarRecieved() {
        return bronzeStarRecieved;
    }

    public void setBronzeStarRecieved(Integer bronzeStarRecieved) {
        this.bronzeStarRecieved = bronzeStarRecieved;
    }
}
