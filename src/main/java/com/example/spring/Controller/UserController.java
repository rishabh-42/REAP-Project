package com.example.spring.Controller;

import com.example.spring.Entities.User;
import com.example.spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getAllUsers")
    List<User> get(){

        List<User> userList= userService.findAllUsers();
        return userList;
    }
}
