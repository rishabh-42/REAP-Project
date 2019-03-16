package com.example.spring.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemUserMapping {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "itemId")
    int itemId;

    @Column(name = "userId")
    int userId;

    @Column(name = "quantity")
    int quantity;


    @Column(name = "createDateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "updateDateTime")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    // we can use cart functionality here

    @Column(name = "inCart")
    boolean inCart;

    @Column(name = "isPlaced")
    boolean isPlaced;

    @Column(name = "isDelivered")
    boolean isDelivered;


}
