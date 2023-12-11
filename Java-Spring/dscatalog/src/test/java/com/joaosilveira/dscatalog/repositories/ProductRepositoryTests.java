package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.entities.Product;
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

    @BeforeEach
    void setUp() throws Exception {
        existisId = 1L;
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
