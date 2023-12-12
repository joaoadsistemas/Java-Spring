package com.joaosilveira.dscatalog.services;

import com.joaosilveira.dscatalog.dtos.ProductDTO;
import com.joaosilveira.dscatalog.entities.Category;
import com.joaosilveira.dscatalog.entities.Product;
import com.joaosilveira.dscatalog.repositories.CategoryRepository;
import com.joaosilveira.dscatalog.repositories.ProductRepository;
import com.joaosilveira.dscatalog.services.exceptions.DatabaseException;
import com.joaosilveira.dscatalog.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.dscatalog.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    private long nonExistingId;
    private long dependentId;
    private long existsId;
    private Category category;

    // nos testes de página se usa esse PageImpl
    private PageImpl<ProductDTO> page;
    private Product product;

    @BeforeEach
    void setUp() throws Exception {
        nonExistingId = 1L;
        existsId = 2L;
        dependentId = 3L;
        product = Factory.createProduct();
        category = Factory.createCategory();
        page = new PageImpl<>(List.of(Factory.createProductDTO()));

        //Deletar um produto e não fazer nada(pois o deletebyid não retorna nada)
        Mockito.doNothing().when(repository).deleteById(existsId);

        // retornar a verificação true se o id existe quando ele existir
        Mockito.when(repository.existsById(existsId)).thenReturn(true);

        // retornar a verificação falsa se o id existe quando ele não existir
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);


        //Lançar a excessão de integridade referencial quando for excluir um id dependente
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);


        //Procurar por página
        Mockito.when(repository.findPageable(any(), any())).thenReturn(page);

        //Save / Update
        Mockito.when(repository.save(any())).thenReturn(product);

        // find by Id quando o ID existe
        Mockito.when(repository.findById(existsId)).thenReturn(Optional.of(product));

        // find by Id quando o ID não existe
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        //getReferencesById
        Mockito.when(repository.getReferenceById(existsId)).thenReturn(product);

        //getReferencesById quando o id nao existir
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        //getReferencesById category
        Mockito.when(categoryRepository.getReferenceById(existsId)).thenReturn(category);

        //getReferencesById quando o id nao existir category
        Mockito.when(categoryRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists() {
        ProductDTO result = service.findById(existsId);

        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    public void findAllPagedShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<ProductDTO> result = service.findPageable("",pageable);

        Assertions.assertNotNull(result);
        Mockito.verify(repository, Mockito.times(1)).findPageable("", pageable);
    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists() {
        ProductDTO productDTO = Factory.createProductDTO();
        ProductDTO result = service.update(productDTO, existsId);

        Assertions.assertNotNull(result);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            ProductDTO productDTO = Factory.createProductDTO();
            service.update(productDTO, nonExistingId);
        });

    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        //Act
        //Assert
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existsId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(existsId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        //Act
        //Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        //Arrange
        Mockito.when(repository.existsById(dependentId)).thenReturn(true);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

        //Act
        //Assert
        Assertions.assertThrows(DatabaseException.class, () -> {
           service.delete(dependentId);
        });
    }

}
