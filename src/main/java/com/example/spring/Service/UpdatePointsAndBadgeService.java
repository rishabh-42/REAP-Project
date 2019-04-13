package com.example.spring.Service;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UpdatePointsAndBadgeService {
    @Autowired
    UserService userService;

    @Autowired
    UserStarCountService userStarCountService;

    @Autowired
    UserStarRecievedService userStarRecievedService;

    public String updatePoints(String email, String points) {
        User user = userService.findByEmail(email);
        int newPoints;
        try {
            newPoints = Integer.parseInt(points);
        } catch (NumberFormatException n) {
            return "Enter Numbers Only";
        } catch (NullPointerException e) {
            return "Please Enter Number";
        }
        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
        userStarReceived.setPoints(newPoints);
        userStarRecievedService.save(userStarReceived);

        return "done";
    }

    public String updateGoldBadges(String email, String count) {
        User user = userService.findByEmail(email);
        int newCount;
        try {
            newCount = Integer.parseInt(count);
        } catch (NumberFormatException n) {
            return "Enter Numbers Only";
        } catch (NullPointerException e) {
            return "Please Enter Number";
        }
        UserStarCount userStarCount = userStarCountService.findByUser(user);
        userStarCount.setGoldStarCount(newCount);
        userStarCountService.saveStars(userStarCount);

        return "done";
    }

    public String updateSilverBadges(String email, String count) {
        User user = userService.findByEmail(email);
        int newCount;
        try {
             newCount = Integer.parseInt(count);
        } catch (NumberFormatException n) {
            return "Enter Numbers Only";
        } catch (NullPointerException e) {
            return "Please Enter Number";
        }
        UserStarCount userStarCount = userStarCountService.findByUser(user);
        userStarCount.setSilverStarCount(newCount);
        userStarCountService.saveStars(userStarCount);
        return "done";
    }

    public String updateBronzeBadges(String email, String count) {
        User user = userService.findByEmail(email);
        int newCount;
        try {
             newCount = Integer.parseInt(count);
        } catch (NumberFormatException n) {
            return "Enter Numbers Only";
        } catch (NullPointerException e) {
            return "Please Enter Number";
        }
        UserStarCount userStarCount = userStarCountService.findByUser(user);
        userStarCount.setBronzeStarCount(newCount);
        userStarCountService.saveStars(userStarCount);
        return "done";
    }
}
