package com.example.spring.Service;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Repositories.BadgesGivenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgesGivenService {
    @Autowired
    BadgesGivenRepository badgesGivenRepository;

    public void save(BadgesGiven badgesGiven){

        badgesGivenRepository.save(badgesGiven);


    }

    public List<BadgesGiven> getAllPosts(){

        return badgesGivenRepository.findAllByOrderByIdDesc();
    }


    public List<BadgesGiven> getListOfGiver(User user){

        return badgesGivenRepository.findByGiver(user);
    }

   public List<BadgesGiven> getListOfReciever(User user){

        return badgesGivenRepository.findByReceiver(user);
    }
}
