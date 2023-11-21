package org.example.db.entities;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private String desccription;
    private String imageUri;

    public Product() {

    }

    public Product(Long id, String name, Double price, String desccription, String imageUri) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desccription = desccription;
        this.imageUri = imageUri;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", desccription='" + desccription + '\'' +
                ", imageUri='" + imageUri + '\'' +
                '}';
    }
}
