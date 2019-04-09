package com.example.spring.Controller;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import com.example.spring.Service.BadgesGivenService;
import com.example.spring.Service.UserService;
import com.example.spring.Service.UserStarCountService;
import com.example.spring.Service.UserStarRecievedService;
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
    UserService userService;

    @Autowired
    UserStarCountService userStarCountService;

    @Autowired
    UserStarRecievedService userStarRecievedService;

    @Autowired
    BadgesGivenService badgesGivenService;


    @PreAuthorize("hasAnyRole('1','2','3','4')")
    @RequestMapping(value ={"/dashboard/profile/"})
    @ResponseBody
    ModelAndView getProfilePage (){


        System.out.println("here iii ========");

            ModelAndView modelAndView =new ModelAndView("pages/profile");

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            System.out.println("=================" +((UserDetails)principal).getUsername());

            User user = userService.findByEmail(((UserDetails)principal).getUsername());

         if(user.isActive()==false) return new ModelAndView("pages/UserInactive");

         modelAndView.addObject("user",user);

            UserStarCount userStarCount = userStarCountService.findByUser(user);
            modelAndView.addObject("userStarCount",userStarCount);

            UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
            modelAndView.addObject("profileUser",userStarReceived);

            List<BadgesGiven> badgesGiven= badgesGivenService.getListOfGiver(user);
            modelAndView.addObject("post",badgesGiven);

            List<BadgesGiven> badgesRecieved = badgesGivenService.getListOfReciever(user);
            modelAndView.addObject("badgesRecieved",badgesRecieved);



            return modelAndView;




    }


}
