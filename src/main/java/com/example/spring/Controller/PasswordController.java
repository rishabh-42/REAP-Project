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
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    @ResponseBody
    public String processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("emailReset") String userEmail, HttpServletRequest request) {
        User optional = userService.findByEmail(userEmail);
        if (optional==null) {
            return "Username does'not exists";
        } else {
            User user =optional;
            user.setResetToken(UUID.randomUUID().toString());
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
        return "Email sent";
    }

    // Display form to reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {
        User user = userService.findByResetToken(token);
        modelAndView.addObject("user",user);
        if (user!=null) { // Token found in DB
            modelAndView.addObject("resetToken", token);

        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }
        modelAndView.setViewName("pages/PasswordReset");
        return modelAndView;
    }

    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
        User user = userService.findByResetToken(requestParams.get("token"));
        // This should always be non-null but we check just in case
        if (user!=null) {
            User resetUser = user;
            // Set new password
            resetUser.setPassword(BCrypt.hashpw(requestParams.get("password"), BCrypt.gensalt(4)));
            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);
            // Save user
            userService.saveUser(resetUser);
            // In order to set a model attribute on a redirect, we must use
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
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("pages/Login");
    }
}