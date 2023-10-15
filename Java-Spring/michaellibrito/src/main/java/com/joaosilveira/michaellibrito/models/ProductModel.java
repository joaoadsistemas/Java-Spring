package com.joaosilveira.michaellibrito.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_products")
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double value;

    public ProductModel() {
    }

    public ProductModel(Long id, String name, Double vallue) {
        this.id = id;
        this.name = name;
        this.value = vallue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double vallue) {
        this.value = vallue;
    }
}
