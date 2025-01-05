package com.spring.events.event;

import com.spring.events.model.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class OrderCreatedEvent {

    private final Order order;

    public OrderCreatedEvent(Order order) {
        this.order = order;
    }

}
