package com.example.spring.Controller;

import com.example.spring.Entities.*;
import com.example.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RevokeRecognitionController {


    @Autowired
    EmailService emailService;

    @Autowired
    UserStarCountService userStarCountService;

    @Autowired
    UserService userService;

    @Autowired
    UserStarRecievedService userStarRecievedService;


    @Autowired
    BadgesGivenService badgesGivenService;

    @PreAuthorize("hasAnyRole('Admin')")
    @PostMapping("/revoke")
    @ResponseBody
    public String revokeRecognition(@RequestParam Map<String,String> postData){
        int postRowId = Integer.parseInt(postData.get("id"));
        String additionalComment =  postData.get("additionalComment");
        String reason = postData.get("reason");
        // Inactivating the post
        BadgesGiven postRow = badgesGivenService.findById(postRowId);
        postRow.setActive(false);
        badgesGivenService.save(postRow);
        //getting star
        Star starType = postRow.getStar();
        // Add one star in giver
        UserStarCount userStarCount = userStarCountService.findByUser(postRow.getGiver());
        if(starType.getName().equals("Gold")) userStarCount.setGoldStarCount(userStarCount.getGoldStarCount()+1);
        if(starType.getName().equals("Silver")) userStarCount.setSilverStarCount(userStarCount.getSilverStarCount()+1);
        if(starType.getName().equals("Bronze")) userStarCount.setBronzeStarCount(userStarCount.getBronzeStarCount()+1);
        userStarCountService.saveStars(userStarCount);
        //reduce one star from receiver
        UserStarReceived userStarReceived = userStarRecievedService.findByUser(postRow.getReceiver());
        if(starType.getName().equals("Gold")) {userStarReceived.setGoldStarRecieved(userStarReceived.getGoldStarRecieved()-1);
        userStarReceived.setPoints(userStarReceived.getPoints()-30);
        }
        if(starType.getName().equals("Silver")) {userStarReceived.setSilverStarRecieved(userStarReceived.getSilverStarRecieved()-1);
            userStarReceived.setPoints(userStarReceived.getPoints()-20);}
        if(starType.getName().equals("Bronze")) {userStarReceived.setBronzeStarRecieved(userStarReceived.getBronzeStarRecieved()-1);
            userStarReceived.setPoints(userStarReceived.getPoints()-10);
        }
        userStarRecievedService.save(userStarReceived);
        // send mail to reciever for reducing a star
        SimpleMailMessage senderEmail = new SimpleMailMessage();
        senderEmail.setTo(postRow.getGiver().getEmail());
        senderEmail.setSubject("Your recognition has been revoked by Admin");
        senderEmail.setText(postRow.getStar().getName()+" Star You gave to " +postRow.getReceiver().getFirstName()+" "+ postRow.getReceiver().getLastName()+" "
                + "has been revoked by Admin\nReason : " + reason + "("+additionalComment+")" + "\nFor more details, Please contact Admin");
        senderEmail.setFrom("no-reply@tothenew.com");
        emailService.sendEmail(senderEmail);
        //Receivers Mail
        SimpleMailMessage recieverEmail = new SimpleMailMessage();
        recieverEmail.setTo(postRow.getReceiver().getEmail());
        recieverEmail.setSubject("Recognition you recieved , has been revoked by Admin");
        recieverEmail.setText(postRow.getStar().getName()+" Star received by you from " +postRow.getGiver().getFirstName()+" "+ postRow.getGiver().getLastName()+" "
                + "has been revoked by Admin\n"+"\nFor more details, Please contact Admin");
        recieverEmail.setFrom("no-reply@tothenew.com");
        emailService.sendEmail(recieverEmail);
       return  "done";
    }
}
