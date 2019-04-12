
package com.example.spring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User {

    public User() {

    }

    public User(User user) {
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.currentRoleId = user.getCurrentRoleId();
        this.active = true;
        this.roles = user.getRoles();
        this.email = user.getEmail();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;


    @Column(nullable = false)
    @Size(min = 3, max = 30, message = "First Name should be of minimum 3 characters")
    String firstName;

    @Size(min = 3, max = 30, message = "Last Name should be of minimum 3 characters")
    String lastName;


    @Email(message = "Please Enter a valid email")
    String email;

    @Size(min = 4, max = 64)
    String password;

    String photo;

    @Transient
    String matchingPassword;


    String confirmationToken;


    String currentRoleId;


    Integer pointSpent = 0;

    String resetToken;

    boolean active;


    @JsonManagedReference
    @OneToOne
    UserStarReceived userStarReceived;


    @JsonManagedReference
    @OneToOne
    UserStarCount userStarCount;

    @Column(name = "createDateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "updateDateTime")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<UserRole> roles = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    List<Order> order = new ArrayList<>();


    public UserStarReceived getUserStarReceived() {
        return userStarReceived;
    }

    public UserStarCount getUserStarCount() {
        return userStarCount;
    }

    public void setUserStarCount(UserStarCount userStarCount) {
        this.userStarCount = userStarCount;
    }

    public void setUserStarReceived(UserStarReceived userStarReceived) {
        this.userStarReceived = userStarReceived;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }


    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Integer getPointSpent() {
        return pointSpent;
    }

    public void setPointSpent(Integer pointSpent) {
        this.pointSpent = pointSpent;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(String currentRoleId) {
        this.currentRoleId = currentRoleId;
    }


}
