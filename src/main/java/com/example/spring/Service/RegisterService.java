package com.example.spring.Service;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserRole;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserStarCountService userStarCountService;

    @Autowired
    private UserStarRecievedService userStarRecievedService;

    @Autowired
    private UserRoleService userRoleService;

    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request, Errors errors) {

        User userExists = userService.findByEmail(user.getEmail());
        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "User Already Registered");
            modelAndView.setViewName("pages/Login");
            bindingResult.reject("email", "Email already registered");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("pages/Login");
        } else {
            user.setActive(false);
            user.setConfirmationToken(UUID.randomUUID().toString());
            userService.saveUser(user);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                    + appUrl + ":8080/confirm?token=" + user.getConfirmationToken());
            registrationEmail.setFrom("rishabh.rajput@tothenew.com");
            emailService.sendEmail(registrationEmail);
            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
            modelAndView.setViewName("pages/Login");
        }
        return modelAndView;
    }

    public ModelAndView showConfirmationPageAndSaveUser(ModelAndView modelAndView, String token) {
        User user = userService.findByConfirmationToken(token);
        modelAndView.addObject("user", user);
        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "This is an invalid confirmation link.");
        } else { // Token found
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(4)));
            user.setActive(true);
//            user.setConfirmationToken("");
            Set<UserRole> roles = new HashSet<>();
            roles.add(userRoleService.getRole("User"));
            user.setRoles(roles);
            user.setPhoto("/assets/profileImages/default.png");
            User newUser = userService.saveUser(user);

            UserStarCount userStarCount = new UserStarCount();
            userStarCount.setUser(newUser);
            userStarCount.setGoldStarCount(userRoleService.getRole("User").getGoldStar());
            userStarCount.setSilverStarCount(userRoleService.getRole("User").getSilverStar());
            userStarCount.setBronzeStarCount(userRoleService.getRole("User").getBronzeStar());
            userStarCountService.saveStars(userStarCount);

            UserStarReceived userStarReceived = new UserStarReceived();
            userStarReceived.setUser(newUser);
            userStarReceived.setGoldStarRecieved(new Integer(0));
            userStarReceived.setBronzeStarRecieved(new Integer(0));
            userStarReceived.setSilverStarRecieved(new Integer(0));
            userStarReceived.setPoints(new Integer(0));
            userStarRecievedService.save(userStarReceived);


            newUser.setUserStarCount(userStarCount);
            newUser.setUserStarReceived(userStarReceived);
            userService.update(user);
            modelAndView.addObject("confirmationToken", "Hurray !! Signup success , login to proceed.");
        }
        modelAndView.setViewName("pages/Login");
        return modelAndView;
    }
}
