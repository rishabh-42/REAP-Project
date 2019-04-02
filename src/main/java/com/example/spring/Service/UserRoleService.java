package com.example.spring.Service;

import com.example.spring.Entities.UserRole;
import com.example.spring.Repositories.UserRepository;
import com.example.spring.Repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRole getRole(int i){

        return  userRoleRepository.findById(i);

    }
}
