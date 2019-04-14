package com.example.spring.Service;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterWallOfFameBetweenDateService {

    @Autowired
    BadgesGivenService badgesGivenService;
    @Autowired
    UserService userService;

    public ModelAndView getPostsBetweenDates( String s, String e){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(s, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(e, formatter);
        List<BadgesGiven> badgesGivens = badgesGivenService.findBetweenDate(dateStart, dateEnd).stream().filter(f -> f.isActive() == true).collect(Collectors.toList());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(((UserDetails) principal).getUsername());
        if (user.isActive() == false) return new ModelAndView("pages/UserInactive");
        ModelAndView modelAndView = new ModelAndView("pages/dateFilterFragment").addObject("post",badgesGivens);
        modelAndView.addObject("user", user);


        return modelAndView ;
    }

    }
