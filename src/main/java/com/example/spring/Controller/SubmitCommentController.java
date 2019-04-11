package com.example.spring.Controller;

import com.example.spring.Entities.BadgesGiven;
import com.example.spring.Entities.User;
import com.example.spring.Repositories.StarRepository;
import com.example.spring.Repositories.UserStarRecievedRepository;
import com.example.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SubmitCommentController {

   @Autowired
   SubmitCommentService submitCommentService;

    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    @ResponseBody
    public String submitComment(@RequestParam Map<String, String> fields) {
        return  submitCommentService.submitComment(fields);
    }
}
