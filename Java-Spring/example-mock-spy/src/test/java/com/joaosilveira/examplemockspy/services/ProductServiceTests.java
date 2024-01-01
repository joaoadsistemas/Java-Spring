package com.joaosilveira.examplemockspy.services;

import com.joaosilveira.examplemockspy.dto.ProductDTO;
import com.joaosilveira.examplemockspy.entities.Product;
import com.joaosilveira.examplemockspy.repositories.ProductRepository;
import com.joaosilveira.examplemockspy.services.exceptions.InvalidDataException;
import com.joaosilveira.examplemockspy.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;


// INDICA QUE NÃO É PRA CARREGAR O CONTEXTO DA APLICAÇÃO
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    // Injetando o service
    @InjectMocks
    private ProductService service;

    // DENTRO DO PRODUCT SERVICE, TEM A INSTANCIA DO REPOSITÓRIO, ENTAO DEVE MOCKAR UM REPOSITORY FALSO AQUI
    @Mock
    private ProductRepository repository;

    // Preparando um produto e um produto DTO
    private Product product;
    private ProductDTO productDTO;

    // Preparando um Id existente e um não existente
    private Long existingId, nonExistingId;

    // Tudo que está aqui dentro vai ser executado antes de cada teste
    @BeforeEach
    void setUp() throws Exception {

        // Basicamente, dentro do ProductService, temos duas chamadas para o ProductRepository, uma de salvar e outra
        // de buscar por id, então nesse caso, tenho que simular o comportamento de cada um desses métodos, pois o
        // ProductRepository é uma classe MOCK(De mentira)

        // Instanciando um product e um productDTO
        product = new Product(1L, "Playstation", 2000.0);
        productDTO = new ProductDTO(product);

        // Colocando quaisquer valores nos IDS, pois o que importa mesmo é o retorno do mock, o valor pode ser
        // qualquer um
        existingId = 1L;
        nonExistingId = 2L;

        // MÈTODO: entity = repository.save(entity);
        // basicamente se ele recebe uma entidade Product (pode ser um Mockito.any(), que significa qualquer coisa),
        // porém ela
        // retorna um product (Product)
        Mockito.when(repository.save(any())).thenReturn(product);

        // O getReferenceById tem dois casos, um quando o Id existe, e um quando o Id não existe
        // MÈTODO: entity = repository.getReferenceById(id);

        // Quando passar um Id existente, ele vai retornar um produto
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(product);

        // Lança uma exceção de Entidade não encontrada quando um Id não existir
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    // teste do insert com sucesso
    @Test
    public void insertShouldReturnProductDTOWhenValidData() {

        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);
        // O método validateData (método que lança exceção caso o nome esteja em branco ou o valor menor que 0) não
        // deve fazer nada quando for passado um productDTO
        Mockito.doNothing().when(serviceSpy).validateData(productDTO);

        // Aqui basicamente estou chamando o método insert do serviceSpy (espião) e instanciando o objeto esperado
        // MÉTODO NO SERVICE: public ProductDTO insert(ProductDTO dto)
        ProductDTO result = serviceSpy.insert(productDTO);

        // O resultado não pode ser nulo
        Assertions.assertNotNull(result);
        // O nome do objeto tem que ser igual a Playstation
        Assertions.assertEquals(result.getName(), "Playstation");
    }

    // teste do insert dando erro
    @Test
    public void insertShouldThrowInvalidDataExceptionWhenProductNameIsBlank() {
        // setando o nome como vazio do productDTO
        productDTO.setName("");

        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);

        // Lança a exceção quando chama o service com o nome vazio
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);

        // Verifica se o serviceSpy está lançando a exceção quando o nome é vazio
        Assertions.assertThrows(InvalidDataException.class, () -> {
            ProductDTO result = serviceSpy.insert(productDTO);
        });
    }


    // teste do insert dando erro
    @Test
    public void insertShouldThrowInvalidDataExceptionWhenProductValueIsNegativeOrZero() {
        // setando o valor como negativo do productDTO
        productDTO.setPrice(-5.0);

        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);

        // Lança a exceção quando chama o service com o preço negativo
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);

        // Verifica se o serviceSpy está lançando a exceção quando o nome é vazio
        Assertions.assertThrows(InvalidDataException.class, () -> {
            ProductDTO result = serviceSpy.insert(productDTO);
        });
    }

    // teste do Update com o Id Existente
    @Test
    public void updateShouldReturnProductDTOWhenIdExistsAndValidData() {
        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);
        // O método validateData (método que lança exceção caso o nome esteja em branco ou o valor menor que 0) não
        // deve fazer nada quando for passado um productDTO
        Mockito.doNothing().when(serviceSpy).validateData(productDTO);

        // Instanciando um productDTO com base em um ID Existente e um ProductDTO vlaido
        // MÉTODO NO SERVICE: public ProductDTO update(Long id, ProductDTO dto)
        ProductDTO result = serviceSpy.update(existingId, productDTO);

        // Verifica se o objto não é nulo e se o id dele é igual o Id existente
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingId);
    }

    @Test
    public void updateShouldThrowInvalidDatabaseExceptionWhenIdExistdAndProductNameIsBlank() {

        // setando o nome como vazio do productDTO
        productDTO.setName("");

        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);

        // Lança a exceção quando chama o service com o nome vazio
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);

        // Verifica se o serviceSpy está lançando a exceção quando o id existe e o nome é vazio
        Assertions.assertThrows(InvalidDataException.class, () -> {
            ProductDTO result = serviceSpy.update(existingId,productDTO);
        });

    }

    @Test
    public void updateShouldThrowInvalidDatabaseExceptionWhenIdExistdAndProductPriceIsNegativeOrZero() {

        // setando o valor como negativo do productDTO
        productDTO.setPrice(-5.0);

        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);

        // Lança a exceção quando chama o service com o valor negativo
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);

        // Verifica se o serviceSpy está lançando a exceção quando o id existe e o valor é negativo
        Assertions.assertThrows(InvalidDataException.class, () -> {
            ProductDTO result = serviceSpy.update(existingId, productDTO);
        });

    }


    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExistsAndValidData() {
        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);
        // O método validateData (método que lança exceção caso o nome esteja em branco ou o valor menor que 0) não
        // deve fazer nada quando for passado um productDTO
        Mockito.doNothing().when(serviceSpy).validateData(productDTO);

        // Verifica se o serviceSpy está lançando a exceção quando o id não existe e o dto é valido
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            ProductDTO result = serviceSpy.update(nonExistingId, productDTO);
        });
    }

    @Test
    public void updateShouldThrowInvalidDatabaseExceptionWhenIdDoesNotExistdAndProductNameIsBlank() {

        // setando o nome como vazio do productDTO
        productDTO.setName("");

        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);

        // Lança a exceção quando chama o service com o nome vazio
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);

        // Verifica se o serviceSpy está lançando a exceção quando o id existe e o valor é negativo
        Assertions.assertThrows(InvalidDataException.class, () -> {
            ProductDTO result = serviceSpy.update(nonExistingId, productDTO);
        });

    }

    @Test
    public void updateShouldThrowInvalidDatabaseExceptionWhenIdDoesNotExistdAndProductPriceIsNegativeOrZero() {

        // setando o valor como negativo do productDTO
        productDTO.setPrice(-5.0);

        // Serve para mockar o service para eu poder usar os métodos dele (o spy é um espião de classe)
        ProductService serviceSpy = Mockito.spy(service);

        // Lança a exceção quando chama o service com o valor negativo
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);

        // Verifica se o serviceSpy está lançando a exceção quando o id existe e o valor é negativo
        Assertions.assertThrows(InvalidDataException.class, () -> {
            ProductDTO result = serviceSpy.update(nonExistingId, productDTO);
        });

    }



}
