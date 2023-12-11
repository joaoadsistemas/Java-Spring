package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.entities.Product;
import com.joaosilveira.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    // instancia o repository de verdade, pois ele vai levar em conta o arquivo IMPORT.SQL para fazer as querys
    @Autowired
    private ProductRepository repository;

    private long existisId;
    private long countTotalProducts;

    // antes de cada teste irá rodar esse codigo
    @BeforeEach
    void setUp() throws Exception {
        existisId = 1L;
        countTotalProducts = 25L;
    }

    @Test
    public void findShouldOptionalEmptyWhenIdNotExists() {
        // Arrange
        long inexistsId = 0L;

        //Act
        Optional<Product> optional = repository.findById(inexistsId);


        //Assert
        Assertions.assertTrue(optional.isEmpty());
    }

    @Test
    public void findShouldCallProductWhenIdExists() {
        //Act
        Optional<Product> optional = repository.findById(existisId);

        //Assert
        Assertions.assertNotNull(optional);
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        //Arrange
        Product product = Factory.createProduct();
        product.setId(null);

        // Act
        product = repository.save(product);

        //Assert
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        //Act
        repository.deleteById(existisId);
        Optional<Product> result = repository.findById(existisId);

        //Assert
        Assertions.assertFalse(result.isPresent());
    }



}
