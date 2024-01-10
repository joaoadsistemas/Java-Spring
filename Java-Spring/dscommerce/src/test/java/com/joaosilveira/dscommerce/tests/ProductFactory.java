package com.joaosilveira.dscommerce.tests;

import com.joaosilveira.dscommerce.dto.ProductDTO;
import com.joaosilveira.dscommerce.entities.Category;
import com.joaosilveira.dscommerce.entities.Product;

public class ProductFactory {

    public static Product createProduct() {
        Category category = CategoryFactory.createCategory();
        Product product =  new Product(1L, "Xbox-One", "Lorem Ipsum", 2000.0, "https://img.com");
        product.getCategories().add(category);
        return product;
    }

    public static ProductDTO createProductDTO() {
        return new ProductDTO(ProductFactory.createProduct());
    }

}
