package com.joaosilveira.dscommerce.repositories;

import com.joaosilveira.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
