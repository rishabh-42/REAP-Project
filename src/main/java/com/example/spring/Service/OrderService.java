package com.example.spring.Service;

import com.example.spring.Entities.Order;
import com.example.spring.Entities.User;
import com.example.spring.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findAllByUser(User user) {
        return orderRepository.findByUserOrderByIdDesc(user);
    }
}

