package com.example.spring.Service;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import com.example.spring.Repositories.UserStarRecievedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStarRecievedService {

    @Autowired
    UserStarRecievedRepository userStarRecievedRepository;

    public void giveStar(User user, String star)
    {
        UserStarReceived userStarReceived =   userStarRecievedRepository.findByUser(user);

        if(star.equals("Gold"))
        {
            userStarReceived.setGoldStarRecieved(userStarReceived.getGoldStarRecieved()+1);
            userStarReceived.setPoints(userStarReceived.getPoints()+30);
        }
        else if(star.equals("Silver")){

            userStarReceived.setSilverStarRecieved(userStarReceived.getSilverStarRecieved()+1);
            userStarReceived.setPoints(userStarReceived.getPoints()+20);
        }

        else if(star.equals("Bronze")){
            userStarReceived.setBronzeStarRecieved(userStarReceived.getBronzeStarRecieved()+1);
            userStarReceived.setPoints(userStarReceived.getPoints()+10);
        }

        userStarRecievedRepository.save(userStarReceived);


    }
    public void save(UserStarReceived userStarReceived){
        userStarRecievedRepository.save(userStarReceived);
    }

    public UserStarReceived findByUser(User user)
    {
        return userStarRecievedRepository.findByUser(user);
    }



    public List<UserStarReceived> getSomeUser(){

        return userStarRecievedRepository.findFirst6ByOrderByPointsDesc();
    }
}
