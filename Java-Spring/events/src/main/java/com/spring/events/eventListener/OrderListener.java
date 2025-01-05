package com.spring.events.eventListener;

import com.spring.events.event.OrderCreatedEvent;
import com.spring.events.event.OrderUpdatedEvent;
import com.spring.events.model.Order;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreUpdate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderListener(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostUpdate
    public void onPostUpdate(Order order) {
        System.out.println("PostUpdate event");
        applicationEventPublisher.publishEvent(new OrderUpdatedEvent(order));
    }
}
