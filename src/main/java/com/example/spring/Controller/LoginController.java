package com.example.spring.Controller;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import com.example.spring.Repositories.UserRepository;

import com.example.spring.Service.BadgesGivenService;
import com.example.spring.Service.UserService;
import com.example.spring.Service.UserStarCountService;
import com.example.spring.Service.UserStarRecievedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserStarRecievedService userStarRecievedService;
    @Autowired
    private UserStarCountService userStarCountService;
    @Autowired
    private BadgesGivenService badgesGivenService;

    @RequestMapping(value = {"/loginSignup", "/"}, method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("pages/Login");
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('User','Admin','PracticeHead','Supervisor')")
    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("pages/Dashboard");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(((UserDetails) principal).getUsername());
        if (user.isActive() == false) return new ModelAndView("pages/UserInactive");

        modelAndView.addObject("user", user);
        UserStarCount userStarCount = userStarCountService.findByUser(user);
        modelAndView.addObject("userStarCount", userStarCount);
        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
        modelAndView.addObject("starRecieved", userStarReceived);
        List<BadgesGiven> post = badgesGivenService.getAllPosts();
        modelAndView.addObject("post", post);
        List<UserStarReceived> topUsers = userStarRecievedService.getSomeUser();
        modelAndView.addObject("topUsers", topUsers);
        List<User> allUsers = userService.findAllUsers();
        modelAndView.addObject("allUsers", allUsers);
        return modelAndView;
    }

}
