package com.example.spring.Controller;

import com.example.spring.Models.User;
import com.example.spring.Service.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class Login{

    @Autowired
    Registration registration;

    @RequestMapping(value="/loginSignup", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView, User user) {

//        ModelAndView modelAndView = new ModelAndView("pages/Login");
            modelAndView.addObject("user",user);
            modelAndView.setViewName("pages/Login");

        return modelAndView;

    }

//
//    @RequestMapping(value="/loginSignup", method = RequestMethod.POST)
//    public ModelAndView signUp(WebRequest request, Model model) {
//
//        ModelAndView modelAndView = new ModelAndView("pages/Login");
//        model.addAttribute("user",new User());
//        modelAndView.addObject("user",new User());
//
//        return modelAndView;
//
//    }

    @RequestMapping(value = "loginSignup", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid User accountDto,
             BindingResult result, WebRequest request, Errors errors) {
//        System.out.println("caleed save" + accountDto.getFirstName() + accountDto.getPassword());

//        System.out.println();
        User registered =null;
        if (!result.hasErrors()) {
//            System.out.println("yha bhiii ayeeeeeeee");
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
//            System.out.println("null huaa");

        }

        if (result.hasErrors()) {
            return new ModelAndView("pages/Login", "user", accountDto );
        }
        else {
            return new ModelAndView("pages/Dashboard", "user", accountDto);
        }

        // rest of the implementation

    }

    private User createUserAccount(User accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = registration.registerNewUserAccount(accountDto);
//            System.out.println("ho gya save");
        } catch (Exception e){

        }
        return registered;
    }

    @PreAuthorize("hasAnyRole('1')")
    @RequestMapping(value="/dashboard")
    public ModelAndView dashboard() {

        ModelAndView modelAndView = new ModelAndView("pages/Dashboard");
        return modelAndView;

    }




}
