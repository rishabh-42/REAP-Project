package com.example.spring.Entities;


import com.example.spring.others.CustomAnnotations.PasswordMatches;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@PasswordMatches
public class User {

    @Id


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    int userId;

    //    @NotNull
//    @NotEmpty
    @Column(name = "firstName")
    String firstName;
    //
//    @NotNull
//    @NotEmpty
    @Column(name = "lastName")
    String lastName;

//    @NotNull
//    @NotEmpty

    @Column(name = "email")
    String email;

    //    @NotNull
//    @NotEmpty
    @Column(name = "mobile")
    String mobile;

    //    @NotNull
//    @NotEmpty
    @Size(min=2,message = "sahi se ddal")
    @Column(name = "password")
    String password;

    @Transient
    String matchingPassword;



    @Column(name = "photo")
    String photo;

    @Column(name = "active")
    int active;

    @Column(name = "currentRoleId")
    int currentRoleId;

    @Column(name = "registrationCompleted")
    boolean registrationCompleted;

    @Column(name = "enabled")
    private boolean enabled;

    @Column
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    //Mapping -> bi directional
    @OneToOne(mappedBy = "user")
    BadgeBalance badgeBalance;

    @Transient
    String confirmPass;

    @Column
    private String resetToken;

    @Column

    private  String confirmationToken;

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }


    public User() {

    }

    public User(User user) {

        System.out.println("Constrc called");
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.currentRoleId = user.getCurrentRoleId();
        this.active = 1;

//        System.out.println("id "+ user.getCurrentRoleId());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getActive() {
        return active;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }


    public void setActive(int active) {
        this.active = active;
    }


    public int getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(int currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public boolean isRegistrationCompleted() {
        return registrationCompleted;
    }

    public void setRegistrationCompleted(boolean registrationCompleted) {
        this.registrationCompleted = registrationCompleted;
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

    public BadgeBalance getBadgeBalance() {
        return badgeBalance;
    }

    public void setBadgeBalance(BadgeBalance badgeBalance) {
        this.badgeBalance = badgeBalance;
    }
}
