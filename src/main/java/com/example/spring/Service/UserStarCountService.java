package com.example.spring.Service;

import com.example.spring.Entities.Star;
import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Repositories.UserStarCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStarCountService {

    @Autowired
    private UserStarCountRepository userStarCountRepository;

    public UserStarCount findByUser(User user) {
        return userStarCountRepository.findByUser(user);
    }

    public void saveStars(UserStarCount userStarCount) {
        userStarCountRepository.save(userStarCount);
    }

    public void update(User user, String star) {
        UserStarCount userStarCount = userStarCountRepository.findByUser(user);
        if (star.equals("Gold")) {
            userStarCount.setGoldStarCount(userStarCount.getGoldStarCount() - 1);
        } else if (star.equals("Silver")) {
            userStarCount.setSilverStarCount(userStarCount.getSilverStarCount() - 1);
        } else if (star.equals("Bronze")) {
            userStarCount.setBronzeStarCount(userStarCount.getBronzeStarCount() - 1);
        }
        userStarCountRepository.save(userStarCount);
    }
}
