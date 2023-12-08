package com.joaosilveira.dscatalog.services;

import com.joaosilveira.dscatalog.repositories.CategoryRepository;
import com.joaosilveira.dscatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


}
