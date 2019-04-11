package com.example.spring.Service;


import com.example.spring.Entities.User;
import com.example.spring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> findAllUsers(){
        return userRepository.findAll();
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
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public void update(User user){
        userRepository.save(user);
    }
}
