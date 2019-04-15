package com.example.spring.Service;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Repositories.StarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SubmitCommentService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private BadgesGivenService badgesGivenService;

    @Autowired
    private StarRepository starRepository;

    @Autowired
    private UserStarCountService userStarCountService;

    @Autowired
    private UserStarRecievedService userStarRecievedService;


    Logger logger = LoggerFactory.getLogger(SubmitCommentService.class);
    public String submitComment(Map<String, String> fields) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (fields.get("email").equals(((UserDetails) principal).getUsername())) {
            return "cannot recognize yourself";
        }
        User userGiver = userService.findByEmail(((UserDetails) principal).getUsername());
        User userReceiver = userService.findByEmail(fields.get("email"));
        if (userReceiver == null || fields.get("badge").equals("")) {
            return "Selected newer doesn't exists";
        }
        if (fields.get("badge").equals("Gold")) {
            if (userStarCountService.findByUser(userGiver).getGoldStarCount() <= 0) {
                return "Not enough stars";
            }
        } else if (fields.get("badge").equals("Silver")) {
            if (userStarCountService.findByUser(userGiver).getSilverStarCount() <= 0) {
                return "Not enough stars";
            }
        } else if (fields.get("badge").equals("Bronze")) {
            if (userStarCountService.findByUser(userGiver).getBronzeStarCount() <= 0) {
                return "Not enough stars";
            }
        }
        logger.info("Submitting comment by " + userGiver.getEmail()+ " to "+ userReceiver.getEmail());

        BadgesGiven badgesGiven = new BadgesGiven();
        badgesGiven.setGiver(userGiver);
        badgesGiven.setReceiver(userReceiver);
        badgesGiven.setComment(fields.get("reason"));
        badgesGiven.setStar(starRepository.findByName(fields.get("badge")));
        badgesGiven.setFlag(true);
        badgesGiven.setActive(true);
        badgesGivenService.save(badgesGiven);
        //reducing stars from sender
        userStarCountService.update(userGiver, fields.get("badge"));
        //Giving star to reciever
        userStarRecievedService.giveStar(userReceiver, fields.get("badge"));
        SimpleMailMessage commentEmail = new SimpleMailMessage();
        commentEmail.setTo(fields.get("email"));
        commentEmail.setSubject("You have been recognised.");
        commentEmail.setText("You recieved a " + fields.get("badge") + " star from " + userGiver.getFirstName() + " " + userGiver.getLastName() + "\n"
                + "Message : " + fields.get("reason"));
        commentEmail.setFrom("no-reply@tothenew.com");
        emailService.sendEmail(commentEmail);
        return "success";
    }

}
