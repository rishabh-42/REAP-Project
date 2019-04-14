package com.example.spring.Controller;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Service.FilterWallOfFameBetweenDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FilterWallOfFameBetweenDateController {
    @Autowired
    FilterWallOfFameBetweenDateService filterWallOfFameBetweenDateService;

    @PreAuthorize("hasAnyRole('User','Admin','PracticeHead','Supervisor')")
    @RequestMapping(value = "/filterBetweenDate",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getPostsBetweenDates(@RequestParam("startDate") String s, @RequestParam("endDate") String e){

        return filterWallOfFameBetweenDateService.getPostsBetweenDates(s,e);
    }
}
