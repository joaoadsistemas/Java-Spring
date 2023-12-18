package com.joaosilveira.dscatalog.dtos;

import com.joaosilveira.dscatalog.entities.Category;
import com.joaosilveira.dscatalog.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

// ANOTANDO O DTO COM OS BEAN VALIDATIONS
public class ProductDTO {

    private Long id;

    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @NotBlank(message = "Campo requerido")
    private String description;

    @Positive(message = "O valor inserido deve ser positivo")
    private Double price;
    private String imgUrl;

    @PastOrPresent(message = "A data do produto não pode ser futura")
    private Instant date;
    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO() {

    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.description = entity.getDescription();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();

        for (Category category: entity.getCategories()) {
            categories.add(new CategoryDTO(category));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }
}
