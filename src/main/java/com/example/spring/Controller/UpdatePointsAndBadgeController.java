package com.example.spring.Controller;

import com.example.spring.Service.UpdatePointsAndBadgeService;
import com.example.spring.Service.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UpdatePointsAndBadgeController {
    @Autowired
    private UpdatePointsAndBadgeService updatePointsAndBadgeService;

    @PreAuthorize("hasAnyRole('Admin')")
    @RequestMapping(value = "/updatePoints",method = RequestMethod.POST)
    @ResponseBody
    public String updatePoints(@RequestParam("email") String email, @RequestParam("points") String points){
        return updatePointsAndBadgeService.updatePoints(email,points);
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @RequestMapping(value = "/updateGoldBadges",method = RequestMethod.POST)
    @ResponseBody
    public String updateGoldBadges(@RequestParam("email") String email, @RequestParam("count") String count){
        return updatePointsAndBadgeService.updateGoldBadges(email,count);
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @RequestMapping(value = "/updateSilverBadges",method = RequestMethod.POST)
    @ResponseBody
    public String updateSilverBadges(@RequestParam("email") String email, @RequestParam("count") String count){
        return updatePointsAndBadgeService.updateSilverBadges(email,count);
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @RequestMapping(value = "/updateBronzeBadges",method = RequestMethod.POST)
    @ResponseBody
    public String updateBronzeBadges(@RequestParam("email") String email, @RequestParam("count") String count){
        return updatePointsAndBadgeService.updateBronzeBadges(email,count);
    }
}
