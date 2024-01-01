package com.joaosilveira.examplemockspy.repositories;

import com.joaosilveira.examplemockspy.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
