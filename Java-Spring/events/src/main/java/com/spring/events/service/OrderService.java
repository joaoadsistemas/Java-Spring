package com.spring.events.service;

import com.spring.events.model.OrderResponse;
import com.spring.events.model.OrderStatus;
import com.spring.events.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponse updateOrder(@NotNull Long id) {
        var order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return OrderResponse.toResponse(order);
    }
}
