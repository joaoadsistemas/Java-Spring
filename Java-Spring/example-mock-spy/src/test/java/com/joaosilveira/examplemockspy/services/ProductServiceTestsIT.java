package com.joaosilveira.examplemockspy.services;

import com.joaosilveira.examplemockspy.dto.ProductDTO;
import com.joaosilveira.examplemockspy.entities.Product;
import com.joaosilveira.examplemockspy.repositories.ProductRepository;
import com.joaosilveira.examplemockspy.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

// @SpringBootTest É USADO PARA FAZER TESTES DE INTEGRAÇÃO (SEM MOCK, COM AS DEPENDENCIAS VERDADEIRAS)
@SpringBootTest
@Transactional
public class ProductServiceTestsIT {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    private Long existsId;
    private Long nonExistsId;

    @BeforeEach
    void setUp() throws Exception {
        existsId = 1L;
        nonExistsId = 1000L;
    }

    // 1. insertShouldReturnProductDTO
    // Verifica se o método insert retorna corretamente um ProductDTO em um ambiente de teste de integração real.
    // Método de Teste: insert.
    // Resultado Esperado: Um ProductDTO não nulo e com o nome igual a "Carro".
    @Test
    public void insertShouldReturnProductDTO() {
        ProductDTO dto = new ProductDTO(1L, "Carro", 20000.0);

        ProductDTO result = service.insert(dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Carro", result.getName());
    }


    // 2. updateShouldReturnProductDTOWhenIdExists
    // Verifica se o método update retorna corretamente um ProductDTO quando o ID existe em um ambiente de teste de integração real.
    // Método de Teste: update.
    // Resultado Esperado: Um ProductDTO não nulo e com o nome igual a "Moto".
    @Test
    public void updateShouldReturnProductDTOWhenIdExists() {
        ProductDTO dto = new ProductDTO(1L, "Carro", 20000.0);
        dto.setName("Moto");

        ProductDTO result = service.update(existsId, dto);

        Assertions.assertEquals(1L, existsId);
        Assertions.assertEquals("Moto", result.getName());
    }

    // 3. updateShouldThrowEntityNotFoundExceptionWhenIdDoesNotExists
    // Verifica se o método update lança a exceção correta quando o ID não existe em um ambiente de teste de integração real.
    // Método de Teste: update.
    // Resultado Esperado: Lançamento de ResourceNotFoundException.
    @Test
    public void updateShouldThrowEntityNotFoundExceptionWhenIdDoesNotExists() {
        ProductDTO dto = new ProductDTO(1L, "Carro", 20000.0);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            ProductDTO result = service.update(nonExistsId, dto);
        });
    }

}
