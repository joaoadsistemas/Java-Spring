package com.joaosilveira.projectOrders.repositories;

import com.joaosilveira.projectOrders.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
