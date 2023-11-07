package com.joaosilveira.projectOrders.repositories;

import com.joaosilveira.projectOrders.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
