package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.dtos.ProductDTO;
import com.joaosilveira.dscatalog.entities.Category;
import com.joaosilveira.dscatalog.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "SELECT new com.joaosilveira.dscatalog.dtos.ProductDTO(obj) " +
            "FROM Product obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))",
    countQuery = "SELECT COUNT(obj) FROM Product obj JOIN obj.categories")
    Page<ProductDTO> findPageable(String name, Pageable pageable);
}
