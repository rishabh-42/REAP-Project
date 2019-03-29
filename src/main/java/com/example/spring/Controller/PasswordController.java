package com.example.spring.Controller;

import com.example.spring.Entities.User;
import com.example.spring.Service.EmailService;
import com.example.spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    private BCryptPasswordEncoder bCryptPasswordEncoder;



    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("emailReset") String userEmail, HttpServletRequest request) {

        System.out.println(userEmail);
        // Lookup user in database by e-mail
        User optional = userService.findByEmail(userEmail);

        if (optional==null) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {

            // Generate random 36-character string token for reset password
            User user =optional;

            user.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userService.saveUser(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + ":8080/reset?token=" + user.getResetToken());

            emailService.sendEmail(passwordResetEmail);

            // Add success message to view
            modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }


        modelAndView.setViewName("pages/Login");
        modelAndView.addObject("user",new User());
        return modelAndView;

    }

    // Display form to reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        System.out.println(token);
        User user = userService.findByResetToken(token);
        modelAndView.addObject("user",user);

        if (user!=null) { // Token found in DB
            System.out.println("user found in db");
            modelAndView.addObject("resetToken", token);

        } else { // Token not found in DB
            System.out.println("user not found in db");
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        modelAndView.setViewName("pages/PasswordReset");
        return modelAndView;
    }

    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {




        // Find the user associated with the reset token
        User user = userService.findByResetToken(requestParams.get("token"));

        System.out.println("here "+ user);
        // This should always be non-null but we check just in case
        if (user!=null) {

            User resetUser = user;

            System.out.println("user not null");
            // Set new password
            resetUser.setPassword(BCrypt.hashpw(requestParams.get("password"), BCrypt.gensalt(4)));

            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);

            // Save user
            userService.saveUser(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            modelAndView.addObject("user",new User());
            redir.addFlashAttribute("successMessageReset", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("pages/Login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessageReset", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("pages/Login");
        }

        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("pages/Login");
    }
}