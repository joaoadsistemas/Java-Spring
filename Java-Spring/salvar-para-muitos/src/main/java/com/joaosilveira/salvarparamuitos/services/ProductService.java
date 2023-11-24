package com.joaosilveira.salvarparamuitos.services;

import com.joaosilveira.salvarparamuitos.dto.CategoryDTO;
import com.joaosilveira.salvarparamuitos.dto.ProductDTO;
import com.joaosilveira.salvarparamuitos.entities.Category;
import com.joaosilveira.salvarparamuitos.entities.Product;
import com.joaosilveira.salvarparamuitos.repositories.CategoryRepository;
import com.joaosilveira.salvarparamuitos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ProductDTO insert(ProductDTO dto) {

        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        for (CategoryDTO catDTO : dto.getCategories()) {
            Category cat = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(cat);
        }

        entity = productRepository.save(entity);

        return new ProductDTO(entity);
    }
}
