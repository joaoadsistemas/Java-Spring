package com.joaosilveira.projectOrders.services;

import com.joaosilveira.projectOrders.entities.Category;
import com.joaosilveira.projectOrders.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        var categoryOpt = categoryRepository.findById(id);
        return categoryOpt.get();
    }


}
