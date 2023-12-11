package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    // instancia o repository de verdade, pois ele vai levar em conta o arquivo IMPORT.SQL para fazer as querys
    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        //Arrange
        long existisId = 1L;

        //Act
        repository.deleteById(1L);
        Optional<Product> result = repository.findById(existisId);

        //Assert
        Assertions.assertFalse(result.isPresent());
    }

}
