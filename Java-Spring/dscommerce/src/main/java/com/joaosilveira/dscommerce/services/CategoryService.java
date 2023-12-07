package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.CategoryDTO;
import com.joaosilveira.dscommerce.dto.ProductDTO;
import com.joaosilveira.dscommerce.dto.ProductMinDTO;
import com.joaosilveira.dscommerce.entities.Category;
import com.joaosilveira.dscommerce.entities.Product;
import com.joaosilveira.dscommerce.repositories.CategoryRepository;
import com.joaosilveira.dscommerce.repositories.ProductRepository;
import com.joaosilveira.dscommerce.services.exceptions.DatabaseException;
import com.joaosilveira.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<CategoryDTO> findAll() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(CategoryDTO::new).toList();
    }


}
