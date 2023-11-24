package com.joaosilveira.dscommerce.dto;

import com.joaosilveira.dscommerce.entities.Product;
import jakarta.validation.constraints.*;

public class ProductDTO {

    // TODAS AS VALIDAÇÕES SÃO FEITAS NO DTO

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres")
    private String name;

    @NotBlank(message = "Campo requerido")
    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    private String description;

    @Positive(message = "O preço deve ser positivo")
    private Double price;
    private String imgUrl;

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }

    // NO DTO SÓ SE USA GET

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
}
