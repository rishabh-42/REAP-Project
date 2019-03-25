package com.example.spring.Repositories;

import com.example.spring.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {


    Optional<User> findByFirstName(String firstName);

    @Override
    <S extends User> S save(S entity);
}
