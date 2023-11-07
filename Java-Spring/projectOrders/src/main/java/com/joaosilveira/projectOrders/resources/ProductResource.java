package com.joaosilveira.projectOrders.resources;

import com.joaosilveira.projectOrders.entities.Product;
import com.joaosilveira.projectOrders.repositories.UserRepository;
import com.joaosilveira.projectOrders.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.findByID(id));
    }
}
