package com.example.spring.Repositories;

import com.example.spring.Entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByName(String name);

    UserRole findByPriority(int i);

}
