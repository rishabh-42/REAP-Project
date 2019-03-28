package com.example.spring.Controller;

import com.example.spring.Entities.User;
import com.example.spring.Service.EmailService;
import com.example.spring.Service.UserService;
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
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterController {


    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;




    // Process form input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request, Errors errors) {

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
            user.setEnabled(false);

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
            user.setEnabled(true);

            // Save user
            userService.saveUser(user);
            modelAndView.addObject("confirmationToken", "Hurray !! Signup success , login to proceed.");
        }

        modelAndView.setViewName("pages/Login");
        return modelAndView;
    }

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {

        modelAndView.setViewName("confirm");



        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }

}