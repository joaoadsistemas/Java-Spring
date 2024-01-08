package com.joaosilveira.mytestsintegrationunitary.services;

import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.repositories.MovieRepository;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

// @SpringBootTest É USADO PARA FAZER TESTES DE INTEGRAÇÃO (SEM MOCK, COM AS DEPENDENCIAS VERDADEIRAS)
@SpringBootTest
@Transactional
public class MovieServiceTestsIT {

    @Autowired
    private MovieService service;

    private Long existsId;
    private Long nonExistsId;

    @BeforeEach
    void setUp() throws Exception {
        existsId = 1L;
        nonExistsId = 1000L;
    }


    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() {

        // chama o movieService.findMovieById passando um id existente
        MovieDTO result = service.findMovieById(existsId);

        // verifica se o resultado não é nulo e se o Id é igual a 1L
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        // quando passar um id inexistente no movieService.findMovieById deve lançar uma exception
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = service.findMovieById(nonExistsId);
        });

    }

    @Test
    public void insertShouldReturnMovieDTO() {
        // cria um DTO
        MovieDTO dto = new MovieDTO(1L, "Cars 2", "Lorem");

        // chama o movieService.insertMovie passando um DTO
        MovieDTO result = service.insertMovie(dto);

         // verifica se o resultado não é nulo
        Assertions.assertNotNull(result);
        // verifica se o id do resultado é igual ao id do DTO
        Assertions.assertEquals(dto.getId(), result.getId());
    }

    @Test
    public void updateShouldReturnMovieDTOWhenIdExists() {

        // cria um DTO
        MovieDTO dto = new MovieDTO(1L, "Cars 2", "Lorem");

        // chama o movieService.updateMovie passando um id existente e um DTO
        MovieDTO result = service.updateMovie(existsId, dto);

        // verifica se o resultado não é nulo
        Assertions.assertNotNull(result);
        // verifica se o id do resultado é igual o id do dto
        Assertions.assertEquals(dto.getId(), result.getId());

    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
        // cria o DTO
        MovieDTO dto = new MovieDTO(1L, "Cars 2", "Lorem");

        // movieService.updateMovie deve lançar uma exception quando o Id nao existir
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = service.updateMovie(nonExistsId, dto);
        });

    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        // movieService.deleteMovie deve lançar uma exception quando o Id nao existir
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteMovie(nonExistsId);
        });

    }

}
