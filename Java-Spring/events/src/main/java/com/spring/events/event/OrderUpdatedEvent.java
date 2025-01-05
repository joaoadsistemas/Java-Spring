package com.spring.events.event;

import com.spring.events.model.Order;
import com.spring.events.model.OrderStatus;
import lombok.Getter;

@Getter
public class OrderUpdatedEvent {

    private final String customerName;
    private final OrderStatus oldStatus;
    private final OrderStatus newStatus;

    public OrderUpdatedEvent(Order order) {
        this.customerName = order.getCustomerName();
        this.oldStatus = order.getPreviousStatus();
        this.newStatus = order.getStatus();
    }



}
