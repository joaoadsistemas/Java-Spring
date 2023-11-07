package com.joaosilveira.projectOrders.services;

import com.joaosilveira.projectOrders.entities.Product;
import com.joaosilveira.projectOrders.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByID(Long id) {
        var productOpt = productRepository.findById(id);
        return productOpt.get();
    }


}
