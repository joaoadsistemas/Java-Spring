package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
