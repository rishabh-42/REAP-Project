package com.example.spring.Controller;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Repositories.StarRepository;
import com.example.spring.Repositories.UserStarRecievedRepository;
import com.example.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SubmitCommentController {

    @Autowired
    private EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    BadgesGivenService badgesGivenService;

    @Autowired
    StarRepository starRepository;

    @Autowired
    UserStarCountService userStarCountService;

    @Autowired
    UserStarRecievedService userStarRecievedService;

    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    @ResponseBody
    public String submitComment(@RequestParam Map<String, String> fields) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (fields.get("email").equals(((UserDetails) principal).getUsername())) {
            return "cannot recognize yourself";
        }
        User userGiver = userService.findByEmail(((UserDetails) principal).getUsername());
        User userReceiver = userService.findByEmail(fields.get("email"));
        if (userReceiver == null) {
            return "Selected newer doesn't exists";
        }
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
        commentEmail.setText("You recieved a star from " + userGiver.getFirstName() + " " + userGiver.getLastName() + "\n"
                + "Message : " + fields.get("reason"));
        commentEmail.setFrom("no-reply@tothenew.com");
        emailService.sendEmail(commentEmail);
        return "success";
    }
}
