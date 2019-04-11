package com.example.spring.Controller;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserRole;
import com.example.spring.Service.UserRoleService;
import com.example.spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @PreAuthorize("hasAnyRole('User','Admin','PracticeHead','Supervisor')")
    @RequestMapping("/getAllUsers")
    List<User> get(){
        List<User> userList= userService.findAllUsers();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName =((UserDetails)principal).getUsername();
        userList=userList.stream().filter(e->!e.getEmail().equals(userName)).collect(Collectors.toList());
        return userList;
    }

    @PreAuthorize("hasAnyRole('User','Admin','PracticeHead','Supervisor')")
    @RequestMapping("/getUserRoles")
    Set<UserRole> getRoles(@RequestParam("email") String email){
        Set<UserRole> userRoles= userService.findByEmail(email).getRoles();
        return userRoles;
    }
    @PreAuthorize("hasAnyRole('Admin')")
    @PostMapping("/updateRoles")
    public  int updateRoles(@RequestParam("email") String email,@RequestParam("newRoles") String newRoles){
        String roles[] =newRoles.split(" ");
         userRoleService.updateRoles(email,roles);
        return 1;
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @PostMapping("/setActive")
    public int setActive(@RequestParam("email") String email ,@RequestParam("checked") String checked){
        User user = userService.findByEmail(email);
        if(checked.equals("true")){
            user.setActive(true);
        }
        else {
            user.setActive(false);
        }
        userService.saveUser(user);
        return 1;
    }
}
