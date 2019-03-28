package com.example.spring.SecurityConfig;

import com.example.spring.Entities.User;
import com.example.spring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        System.out.println("yaha aya " + username);

        User user = userRepository.findByFirstName(username);

//        System.out.println("Yaha bhi aya");



        if(user==null){
//            System.out.println("nhn mila");
            throw new UsernameNotFoundException("User 404");
        }
        return new CustomUserDetails(user);


    }

}
