package com.joaosilveira.salvarparamuitos.repositories;

import com.joaosilveira.salvarparamuitos.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
