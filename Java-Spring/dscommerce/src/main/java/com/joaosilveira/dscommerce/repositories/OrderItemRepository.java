package com.joaosilveira.dscommerce.repositories;

import com.joaosilveira.dscommerce.entities.Order;
import com.joaosilveira.dscommerce.entities.OrderItem;
import com.joaosilveira.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {


}
