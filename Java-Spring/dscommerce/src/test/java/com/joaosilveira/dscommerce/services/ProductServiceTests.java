package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.ProductDTO;
import com.joaosilveira.dscommerce.dto.ProductMinDTO;
import com.joaosilveira.dscommerce.entities.Category;
import com.joaosilveira.dscommerce.entities.Product;
import com.joaosilveira.dscommerce.repositories.ProductRepository;
import com.joaosilveira.dscommerce.services.exceptions.DatabaseException;
import com.joaosilveira.dscommerce.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.dscommerce.tests.CategoryFactory;
import com.joaosilveira.dscommerce.tests.ProductFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    private Long existingId, nonExistingId, dependentId;

    // Usado para criar uma página de produtos
    private PageImpl<Product> page;

    @BeforeEach
    void setUp() throws Exception {

        product = ProductFactory.createProduct();
        existingId = 1L;
        nonExistingId = 2L;
        // criando a página de produtos colocando uma lista contendo apenas um produto
        page = new PageImpl<>(List.of(product));

        Mockito.when(productRepository.findById(existingId)).thenReturn(Optional.of(product));
        // quando chamar o productRepository.findById passando um ID inexistente, ele irá retornar um Optional vazio
        Mockito.when(productRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // quando chamar o productRepository.searchByName passsando quaiquer argumentos, ela deve retornar uma página
        // de Produtos
        Mockito.when(productRepository.searchByName(any(), any())).thenReturn(page);

        Mockito.when(productRepository.save(any())).thenReturn(product);

        Mockito.when(productRepository.getReferenceById(existingId)).thenReturn(product);
        Mockito.when(productRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(productRepository.existsById(existingId)).thenReturn(true);
        Mockito.when(productRepository.existsById(dependentId)).thenReturn(true);
        Mockito.when(productRepository.existsById(nonExistingId)).thenReturn(false);

        Mockito.doNothing().when(productRepository).deleteById(existingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(dependentId);

    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists() {

        Category category = CategoryFactory.createCategory();
        ProductDTO result = productService.findById(existingId);

        Assertions.assertEquals(result.getId(), product.getId());
        Assertions.assertEquals(result.getName(), product.getName());
        Assertions.assertEquals(result.getDescription(), product.getDescription());
        Assertions.assertEquals(result.getImgUrl(), product.getImgUrl());
        Assertions.assertEquals(result.getCategories().get(0).getName(), category.getName());

    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            ProductDTO result = productService.findById(nonExistingId);
        });
    }

    @Test
    public void findAllShouldReturnPagedProductMinDTO() {
        // cria uma pageable e um name para ser passado no método (o nome deve ser o mesmo que está instanciado na
        // no ProductFactory de Produtos, pois irei pesquisar por ele)
        Pageable pageable = PageRequest.of(0, 12);
        String name = "Xbox-One";

        Page<ProductMinDTO> result = productService.findAll(name, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.iterator().next().getName(), product.getName());
        Assertions.assertEquals(result.getSize(), 1);
    }

    @Test
    public void insertShouldReturnProductDTO() {

        ProductDTO result = productService.insert(ProductFactory.createProductDTO());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), product.getId());
    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists() {
        ProductDTO dto = ProductFactory.createProductDTO();
        ProductDTO result = productService.update(existingId, dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingId);
        Assertions.assertEquals(result.getName(), dto.getName());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            ProductDTO dto = ProductFactory.createProductDTO();
            ProductDTO result = productService.update(nonExistingId, dto);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            productService.delete(existingId);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            productService.delete(dependentId);
        });
    }
}
