package com.example.spring.Entities;



import com.example.spring.utils.elapsedtime.ElapsedDateTimeUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Transient
    String elapsedTime;

    public boolean isActive() {
        return active;
    }

    boolean active;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getGiver() {
        return giver;
    }

    public void setGiver(User giver) {
        this.giver = giver;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {

        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getElapsedTime() {
        setElapsedTime(ElapsedDateTimeUtil.getElapsedTime(getUpdateDateTime()));
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
