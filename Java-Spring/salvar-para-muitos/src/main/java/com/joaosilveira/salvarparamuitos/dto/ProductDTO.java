package com.joaosilveira.salvarparamuitos.dto;

import com.joaosilveira.salvarparamuitos.entities.Category;
import com.joaosilveira.salvarparamuitos.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();

        for (Category cat : entity.getCategories()) {
            categories.add(new CategoryDTO(cat));
        }

    }

    public ProductDTO(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
