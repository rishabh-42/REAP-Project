package com.example.spring.Repositories;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarReceived;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStarRecievedRepository extends JpaRepository<UserStarReceived,Integer> {

    UserStarReceived findByUser(User user);
}
