package com.example.spring.Service;

import com.example.spring.Models.User;
import com.example.spring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Registration {

    @Autowired
    UserRepository userRepository;

    public User registerNewUserAccount (User user){
        return  userRepository.save(user);
    }

}
