package com.joaosilveira.salvarparamuitos.repositories;

import com.joaosilveira.salvarparamuitos.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
