package com.joaosilveira.dscommerce.repositories;

import com.joaosilveira.dscommerce.entities.Order;
import com.joaosilveira.dscommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
