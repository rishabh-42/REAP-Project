package com.example.spring.Service;

import com.example.spring.Models.User;
import com.example.spring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Registration {

    @Autowired
    UserRepository userRepository;

    public User registerNewUserAccount (User user){

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(4)));
        user.setEnabled(true);
        user.setCurrentRoleId(1);

        return  userRepository.save(user);
    }

}
