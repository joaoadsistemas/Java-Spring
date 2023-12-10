package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.dtos.CategoryDTO;
import com.joaosilveira.dscatalog.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT new com.joaosilveira.dscatalog.dtos.CategoryDTO(obj) " +
            "FROM Category obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(concat('%', :name, '%'))",
    countQuery = "SELECT COUNT(obj) FROM Category obj")
    Page<CategoryDTO> findPageable(String name, Pageable pageable);
}
