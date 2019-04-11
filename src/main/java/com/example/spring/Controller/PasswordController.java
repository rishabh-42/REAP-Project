package com.example.spring.Controller;

import com.example.spring.Entities.User;
import com.example.spring.Service.EmailService;
import com.example.spring.Service.PasswordService;
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
    PasswordService passwordService;

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    @ResponseBody
    public String processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("emailReset") String userEmail, HttpServletRequest request) {
        return passwordService.processForgotPasswordForm(modelAndView,userEmail,request);
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
        return  passwordService.setNewPassword(modelAndView,requestParams,redir);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("pages/Login");
    }
}