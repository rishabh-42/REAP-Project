package com.example.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

    @RequestMapping(value="/login")
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView("pages/Login");

        return modelAndView;

    }

    @RequestMapping(value="/dashboard")
    public ModelAndView dashboard() {

        ModelAndView modelAndView = new ModelAndView("pages/Dashboard");
        return modelAndView;

    }




}
