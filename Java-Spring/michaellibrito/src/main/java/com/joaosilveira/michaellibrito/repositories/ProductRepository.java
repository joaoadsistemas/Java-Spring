package com.joaosilveira.michaellibrito.repositories;

import com.joaosilveira.michaellibrito.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
