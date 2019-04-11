package com.example.spring.Repositories;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BadgesGivenRepository extends JpaRepository<BadgesGiven,Integer> {
    List<BadgesGiven> findByActiveOrderByIdDesc(boolean active);
    List<BadgesGiven> findByGiverAndActive(User user,boolean active);

    List<BadgesGiven> findByReceiverAndActive(User user,boolean active);
    @Override
    <S extends BadgesGiven> S save(S entity);

    Optional<BadgesGiven> findById(Integer i);

    List<BadgesGiven> findAllByCreateDateTimeBetween(LocalDateTime startDate ,LocalDateTime endDate);

    List<BadgesGiven> findAllByGiverOrReceiverOrderByIdDesc(User user1,User user2);
}
