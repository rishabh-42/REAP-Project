package com.example.spring.Repositories;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarReceived;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStarRecievedRepository extends JpaRepository<UserStarReceived,Integer> {

    UserStarReceived findByUser(User user);

    List<UserStarReceived> findFirst6ByOrderByGoldStarRecievedDesc();
}
