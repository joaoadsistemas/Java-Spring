package com.joaosilveira.projectOrders.repositories;

import com.joaosilveira.projectOrders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
