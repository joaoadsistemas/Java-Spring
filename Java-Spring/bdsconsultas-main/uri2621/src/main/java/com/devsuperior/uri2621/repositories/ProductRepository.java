package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dtos.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT products.name " +
            "FROM products " +
            "INNER JOIN providers ON products.id_providers = providers.id " +
            "WHERE products.amount BETWEEN :min AND :max " +
            "AND UPPER(providers.name) LIKE CONCAT(UPPER(:initialLetter), '%')")
    List<ProductMinProjection> search1(Integer min, Integer max, String initialLetter);


    @Query("SELECT new com.devsuperior.uri2621.dtos.ProductMinDTO(obj.name) " +
            "FROM Product obj " +
            "WHERE obj.amount BETWEEN :min AND :max " +
            "AND UPPER(obj.provider.name) LIKE CONCAT(UPPER(:initialLetter), '%')")
    List<ProductMinDTO> search2(Integer min, Integer max, String initialLetter);



}
