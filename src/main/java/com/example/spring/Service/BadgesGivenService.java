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

        return badgesGivenRepository.findByActiveOrderByIdDesc(true);
    }


    public List<BadgesGiven> getListOfGiver(User user){

        return badgesGivenRepository.findByGiverAndActive(user,true);
    }

   public List<BadgesGiven> getListOfReciever(User user){

        return badgesGivenRepository.findByReceiverAndActive(user,true);

    }


    public BadgesGiven findById(int id){

        return badgesGivenRepository.findById(id).get();
    }
}
