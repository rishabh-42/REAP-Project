package com.example.spring.Controller;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserRole;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import com.example.spring.Service.*;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class RegisterController {


    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    UserStarCountService userStarCountService;

    @Autowired
    UserStarRecievedService userStarRecievedService;




    // Process form input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request, Errors errors) {

        System.out.println("=======================register called");
        // Lookup user in database by e-mail
        User userExists = userService.findByEmail(user.getEmail());

        System.out.println(userExists);

        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "User Already Registered");
            modelAndView.setViewName("pages/Login");
            bindingResult.reject("email","Email already registered");
        }

        if (bindingResult.hasErrors()) {


            bindingResult.getAllErrors().forEach(System.out::println);
            System.out.println("has errors");
            modelAndView.setViewName("pages/Login");
        } else {

            // new user so we create user and send confirmation e-mail

            // Disable user until they click on confirmation link in email
            user.setActive(false);

            // Generate random 36-character string token for confirmation link
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

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        System.out.println(token);
        System.out.println("dasdas ");
        User user = userService.findByConfirmationToken(token);



        modelAndView.addObject("user",user);

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "This is an invalid confirmation link.");
        } else { // Token found


            // Set new password
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(4)));

            System.out.println(user.getPassword());
            // Set user to enabled
            user.setActive(true);

            Set<UserRole> roles = new HashSet<>();
            roles.add(userRoleService.getRole(1));
            user.setRoles(roles);
            // Save user
            user.setPhoto("assets/profileImages/"+user.getUserId()+".png");



            User newUser = userService.saveUser(user);


            //assigning default stars

            UserStarCount userStarCount = new UserStarCount();


            userStarCount.setUser(newUser);

            userStarCount.setGoldStarCount(userRoleService.getRole(1).getGoldStar());
            userStarCount.setSilverStarCount(userRoleService.getRole(1).getSilverStar());
            userStarCount.setBronzeStarCount(userRoleService.getRole(1).getBronzeStar());


            userStarCountService.saveStars(userStarCount);

            // adding in user star recieved table
            UserStarReceived userStarReceived = new UserStarReceived();
            userStarReceived.setUser(newUser);
            userStarReceived.setGoldStarRecieved(new Integer(0));
            userStarReceived.setBronzeStarRecieved(new Integer(0));
            userStarReceived.setSilverStarRecieved(new Integer(0));
            userStarReceived.setPoints(new Integer(0));

            userStarRecievedService.save(userStarReceived);

            newUser.setUserStarReceived(userStarReceived);
            userService.update(user);








            modelAndView.addObject("confirmationToken", "Hurray !! Signup success , login to proceed.");
        }

        modelAndView.setViewName("pages/Login");
        return modelAndView;
    }




}