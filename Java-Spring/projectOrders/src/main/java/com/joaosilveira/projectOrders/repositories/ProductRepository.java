package com.joaosilveira.projectOrders.repositories;

import com.joaosilveira.projectOrders.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
