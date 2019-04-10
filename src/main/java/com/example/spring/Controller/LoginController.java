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
    UserStarRecievedService userStarRecievedService;

    @Autowired
    UserRepository userRepository;


    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserStarCountService userStarCountService;

    @Autowired
    BadgesGivenService badgesGivenService;

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

        System.out.println("akaka");
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
            System.out.println("akakasasas");

            result.getGlobalErrors().forEach(System.out::println);

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

            registered = userRepository.save(accountDto);
//            System.out.println("ho gya save");
        } catch (Exception e){

        }
        return registered;
    }

    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyRole('1','2','3','4')")
    @RequestMapping(value="/dashboard")
    public ModelAndView dashboard() {


        ModelAndView modelAndView = new ModelAndView("pages/Dashboard");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("=================" +((UserDetails)principal).getUsername());

        User user = userService.findByEmail(((UserDetails)principal).getUsername());

        if(user.isActive()==false) return new ModelAndView("pages/UserInactive");

        modelAndView.addObject("user",user);

        UserStarCount userStarCount = userStarCountService.findByUser(user);
        modelAndView.addObject("userStarCount",userStarCount);

        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
        modelAndView.addObject("starRecieved",userStarReceived);

        List<BadgesGiven> post = badgesGivenService.getAllPosts();
        modelAndView.addObject("post",post);

        List<UserStarReceived> topUsers = userStarRecievedService.getSomeUser();
        modelAndView.addObject("topUsers",topUsers);

        List<User> allUsers = userService.findAllUsers();
        modelAndView.addObject("allUsers",allUsers);







        return modelAndView;

    }





    //reset password
//
//    @RequestMapping(value = "/user/resetPassword",
//            method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse resetPassword(HttpServletRequest request,
//                                         @RequestParam("email") String userEmail) {
//        User user = userRepository.findByEmail(userEmail);
//        if (user == null) {
//
//            System.out.println("user not found");
////            throw new UserNotFoundException();
//        }
//        String token = UUID.randomUUID().toString();
//        createPasswordResetTokenForUser(user, token);
//
//        mailSender.send(constructResetTokenEmail(getAppUrl(request),
//                request.getLocale(), token, user));
//        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null,
//                        request.getLocale()));
//    }
//
//
//    public void createPasswordResetTokenForUser(User user, String token) {
//        PasswordResetToken myToken = new PasswordResetToken(token, user);
//        passwordTokenRepository.save(myToken);
//    }
//
//    private SimpleMailMessage constructResetTokenEmail(
//            String contextPath, Locale locale, String token, User user) {
//        String url = contextPath + "/user/changePassword?id=" +
//                user.getUserId() + "&token=" + token;
//        String message = "fhaljfladfa";
//        return constructEmail("Reset Password", message + " \r\n" + url, user);
//    }
//
//
//
//    private SimpleMailMessage constructEmail(String subject, String body,
//                                             User user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
//        email.setFrom("rishabh.rajput@tothenew.com");
//        return email;
//    }
//


}
