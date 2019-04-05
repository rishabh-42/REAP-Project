package com.example.spring.Repositories;

import com.example.spring.Entities.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star, Integer> {
    Star findByName(String s);
}
