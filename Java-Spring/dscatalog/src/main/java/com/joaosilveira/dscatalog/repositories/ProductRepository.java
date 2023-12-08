package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.entities.Category;
import com.joaosilveira.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
