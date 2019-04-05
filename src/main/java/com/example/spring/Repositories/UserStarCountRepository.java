package com.example.spring.Repositories;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStarCountRepository extends JpaRepository<UserStarCount,Integer> {

    UserStarCount findByUser(User user);
    @Override
    <S extends UserStarCount> S save(S entity);
}
