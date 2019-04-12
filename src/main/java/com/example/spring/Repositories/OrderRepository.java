package com.example.spring.Repositories;

import com.example.spring.Entities.Order;
import com.example.spring.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByUserOrderByIdDesc(User user);
}
