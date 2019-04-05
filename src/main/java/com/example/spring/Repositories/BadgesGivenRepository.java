package com.example.spring.Repositories;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgesGivenRepository extends JpaRepository<BadgesGiven,Integer> {

    List<BadgesGiven> findByGiver(User user);

    List<BadgesGiven> findByReceiver(User user);
    @Override
    <S extends BadgesGiven> S save(S entity);
}
