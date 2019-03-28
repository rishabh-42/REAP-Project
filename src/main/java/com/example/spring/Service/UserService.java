package com.example.spring.Service;


import com.example.spring.Entities.User;
import com.example.spring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User findByFirstName(String name) {
        return userRepository.findByFirstName(name);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByResetToken(String resetToken){
        return userRepository.findByResetToken(resetToken);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


}