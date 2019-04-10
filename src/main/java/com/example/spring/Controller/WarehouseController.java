package com.example.spring.Controller;

import com.example.spring.Entities.Order;
import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import com.example.spring.Service.OrderService;
import com.example.spring.Service.UserService;
import com.example.spring.Service.UserStarCountService;
import com.example.spring.Service.UserStarRecievedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.Arrays;

@Controller
public class WarehouseController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserStarCountService userStarCountService;

    @Autowired
    UserStarRecievedService userStarRecievedService;

    @RequestMapping("/warehouse")
    public ModelAndView getWarehouse(){

        ModelAndView modelAndView = new ModelAndView("pages/Warehouse");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("=================" +((UserDetails)principal).getUsername());

        User user = userService.findByEmail(((UserDetails)principal).getUsername());

        modelAndView.addObject("user",user);

        UserStarCount userStarCount =userStarCountService.findByUser(user);
        modelAndView.addObject("userStarCount",userStarCount);

        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
        modelAndView.addObject("userStarRecieved",userStarReceived);

        return modelAndView;

    }

    @PostMapping("/warehouse")
    @ResponseBody
    public String redeemPoints(@RequestParam("items") String items,@RequestParam("price") String price,@RequestParam("imgUrl") String imgUrl,@RequestParam("totalPrice") String totalPrice){


        int cartPrice = Integer.parseInt(totalPrice);
        String itemArr[]=items.split(" ");

        String imgArr[]=imgUrl.split(" ");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("=================" +((UserDetails)principal).getUsername());

        User user = userService.findByEmail(((UserDetails)principal).getUsername());

        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);

        if(cartPrice>userStarReceived.getPoints()){
            return "not enough points";
        }
        else {

            Order order = new Order();
            order.setItemUrl(Arrays.asList(itemArr));
            order.setTotalPrice(cartPrice);
            order.setUser(user);
            userStarReceived.setPoints(userStarReceived.getPoints()-cartPrice);
            orderService.saveOder(order);


        }



        return "done";

    }
}
