package com.example.spring.Service;

import com.example.spring.Entities.Order;
import com.example.spring.Entities.User;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Entities.UserStarReceived;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Service
public class WarehouseService {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserStarCountService userStarCountService;

    @Autowired
    private UserStarRecievedService userStarRecievedService;

    public ModelAndView getWarehouse() {
        ModelAndView modelAndView = new ModelAndView("pages/Warehouse");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(((UserDetails) principal).getUsername());
        modelAndView.addObject("user", user);
        UserStarCount userStarCount = userStarCountService.findByUser(user);
        modelAndView.addObject("userStarCount", userStarCount);
        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
        modelAndView.addObject("userStarRecieved", userStarReceived);
        return modelAndView;
    }

    public String redeemPoints(@RequestParam("items") String items, @RequestParam("price") String price, @RequestParam("imgUrl") String imgUrl, @RequestParam("totalPrice") String totalPrice) {
        int cartPrice = Integer.parseInt(totalPrice);
        String itemArr[] = items.split(" ");
        String imgArr[] = imgUrl.split(" ");// can be used if required
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(((UserDetails) principal).getUsername());
        UserStarReceived userStarReceived = userStarRecievedService.findByUser(user);
        if (cartPrice > userStarReceived.getPoints()) {
            return "not enough points";
        } else {
            Order order = new Order();
            order.setItemUrl(Arrays.asList(itemArr));
            order.setTotalPrice(cartPrice);
            order.setUser(user);
            userStarReceived.setPoints(userStarReceived.getPoints() - cartPrice);
            userStarRecievedService.save(userStarReceived);
            orderService.saveOrder(order);
        }
        return "done";
    }

}


