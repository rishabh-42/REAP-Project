package com.example.spring.Service;

import com.example.spring.Entities.Order;
import com.example.spring.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(Order order){

        orderRepository.save(order);
    }
}

