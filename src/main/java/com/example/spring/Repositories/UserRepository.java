package com.example.spring.Repositories;

import com.example.spring.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    User findByResetToken(String resetToken);

    List<User> findAll();


}
