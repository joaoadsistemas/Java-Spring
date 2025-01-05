package com.spring.events.model;

public record OrderResponse(
        Long orderId,
        OrderStatus oldStatus,
        OrderStatus newStatus
) {
    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getPreviousStatus(),
                order.getStatus()
        );
    }
}