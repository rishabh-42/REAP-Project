package com.example.spring.Controller;

import com.example.spring.Entities.*;
import com.example.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.ModalExclude;

import java.util.List;

@Controller
public class ProfileController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserStarCountService userStarCountService;

    @Autowired
    private UserStarRecievedService userStarRecievedService;

    @Autowired
    private BadgesGivenService badgesGivenService;

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('User','Admin','PracticeHead','Supervisor')")
    @RequestMapping(value = {"/dashboard/profile/"})
    @ResponseBody
    public ModelAndView getProfilePage() {
        ModelAndView modelAndView = new ModelAndView("pages/profile");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(((UserDetails) principal).getUsername());
        if (user.isActive() == false) return new ModelAndView("pages/UserInactive");
        modelAndView.addObject("user", user);
        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
        modelAndView.addObject("userStarReceived", userStarReceived);
        UserStarCount userStarCount = userStarCountService.findByUser(user);
        modelAndView.addObject("userStarCount", userStarCount);
        List<BadgesGiven> allSharedAndReceived = badgesGivenService.findByGiverOrReciever(user, user);
        modelAndView.addObject("allSharedAndReceived", allSharedAndReceived);
        List<BadgesGiven> given = badgesGivenService.getListOfGiver(user);
        modelAndView.addObject("given", given);
        List<BadgesGiven> received = badgesGivenService.getListOfReciever(user);
        modelAndView.addObject("received", received);
        List<Order> userOrders = orderService.findAllByUser(user);
        modelAndView.addObject("orders", userOrders);
        return modelAndView;
    }
}
