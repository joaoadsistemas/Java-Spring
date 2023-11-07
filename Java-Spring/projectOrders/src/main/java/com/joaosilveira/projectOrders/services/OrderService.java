package com.joaosilveira.projectOrders.services;

import com.joaosilveira.projectOrders.entities.Order;
import com.joaosilveira.projectOrders.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        var orderOptional = orderRepository.findById(id);
        return orderOptional.get();
    }
}
