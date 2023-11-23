package com.joaosilveira.dscommerce.controllers;

import com.joaosilveira.dscommerce.dto.ProductDTO;
import com.joaosilveira.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    // Pageable tem que ser do spring.domain
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto) {
        return service.insert(dto);
    }

}
