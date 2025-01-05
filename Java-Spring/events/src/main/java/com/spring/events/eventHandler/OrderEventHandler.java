package com.spring.events.eventHandler;

import com.spring.events.event.OrderUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventHandler {

    @EventListener
    public void handleOrderUpdated(OrderUpdatedEvent event) {
        log.info("Order Updated Event: {}", event);
        log.info("Previous Status: {}", event.getOldStatus());
        log.info("New Status: {}", event.getNewStatus());
    }
}
