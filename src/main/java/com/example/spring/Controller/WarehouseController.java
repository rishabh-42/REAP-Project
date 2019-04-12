package com.example.spring.Controller;


import com.example.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PreAuthorize("hasAnyRole('User','Admin','PracticeHead','Supervisor')")
    @RequestMapping(value = "/warehouse",method = RequestMethod.GET)
    public ModelAndView getWarehouse() {
        return warehouseService.getWarehouse();
    }

    @PreAuthorize("hasAnyRole('User','Admin','PracticeHead','Supervisor')")
    @RequestMapping(value = "/warehouse",method = RequestMethod.POST)
    @ResponseBody
    public String redeemPoints(@RequestParam("items") String items, @RequestParam("price") String price, @RequestParam("imgUrl") String imgUrl, @RequestParam("totalPrice") String totalPrice) {
        return warehouseService.redeemPoints(items, price, imgUrl, totalPrice);
    }
}
