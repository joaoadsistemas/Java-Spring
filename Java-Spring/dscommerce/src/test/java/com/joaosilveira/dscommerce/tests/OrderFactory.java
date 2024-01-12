package com.joaosilveira.dscommerce.tests;

import com.joaosilveira.dscommerce.entities.*;

import java.time.Instant;

public class OrderFactory {

    public static Order createOrder(User user) {
        Order order = new Order(1L, Instant.now(), OrderStatus.WAITING_PAYMENT, user, new Payment());

        Product product = ProductFactory.createProduct();

        OrderItem orderItem = new OrderItem(order, product, 2, 10.0);
        order.getItems().add(orderItem);

        return order;
    }

}
