package com.joaosilveira.authorizationbaseproject.repositories;

import com.joaosilveira.authorizationbaseproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
