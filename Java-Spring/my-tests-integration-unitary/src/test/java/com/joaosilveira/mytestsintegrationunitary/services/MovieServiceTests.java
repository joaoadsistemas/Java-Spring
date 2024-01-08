package com.joaosilveira.mytestsintegrationunitary.services;

import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.repositories.MovieRepository;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

// usado para testes unitários
@ExtendWith(SpringExtension.class)
public class MovieServiceTests {

    // injeta um mock do movieService
    @InjectMocks
    private MovieService movieService;

    // cria um mock do movieRepository
    @Mock
    private MovieRepository movieRepository;

    private Movie movie;

    private MovieDTO movieDTO;

    private Long existsId;

    private Long nonExistsId;



    @BeforeEach
    void setUp() throws Exception {

        movie = new Movie(1L, "Cars 2", "Lorem ipsum");
        movieDTO = new MovieDTO(1L, "Cars 2", "Lorem Ipsum");
        existsId = 1L;
        nonExistsId = 2L;

        // Optional<Movie> movieOptional = movieRepository.findById(id);
        // quando chamar o movieRepository.findById passando um Id existente ele vai retornar um Optional
        // que contém um movie
        Mockito.when(movieRepository.findById(existsId)).thenReturn(Optional.of(movie));
        //  Movie result = movieOptional.orElseThrow(
        //                () -> new ResourceNotFoundException("Recurso não encontrado")
        //        );
        // quando chamar o movieRepository.findById passando um id não existente ele irá lançar uma exception
        Mockito.doThrow(ResourceNotFoundException.class).when(movieRepository).findById(nonExistsId);

        // (Movie) entity = movieRepository.save(entity)
        // quando chamar o movieRepository.save passando qualquer argumento, ele irá retornar um movie
        Mockito.when(movieRepository.save(any())).thenReturn(movie);

        //  Movie entity = movieRepository.getReferenceById(id)
        // quando chamar o movieRepository.getReferenceById passando um id existente ele deve retornar um movie
        Mockito.when(movieRepository.getReferenceById(existsId)).thenReturn(movie);
        // quando chamar o movieRepository.getReferenceById passando um id inexistente
        // ele deve lnaçar uma exception
        Mockito.doThrow(EntityNotFoundException.class).when(movieRepository).getReferenceById(nonExistsId);

    }

    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() {
        // chama o movieService.findMovieById passando um id existente
        MovieDTO result = movieService.findMovieById(existsId);
        // verifica se o resultado não é nulo
        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        // quando passar um id inexistente no movieService.findMovieById deve lançar uma exception
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = movieService.findMovieById(nonExistsId);
        });

    }

    @Test
    public void insertShouldReturnMovieDTO() {

        // chama o movieService.insertMovie passando um DTO
        MovieDTO result = movieService.insertMovie(movieDTO);

        // verifica se o id do resultado é igual a 1L (Id que coloquei para o DTO))
        Assertions.assertEquals(1L, result.getId());
        // verifica se o nome do filme é igual a Cars 2 (Nome que coloquei para o DTO)
        Assertions.assertEquals("Cars 2", result.getMovieName());
    }

    @Test
    public void updateShouldReturnMovieDTOWhenIdExists() {
        // chama o movieService.updateMovie passando um id existente e um DTO
        MovieDTO result = movieService.updateMovie(existsId, movieDTO);

        // verifica se o resultado não é nulo
        Assertions.assertNotNull(result);
        // verifica se o id do dto é igual o id que passei
        Assertions.assertEquals(existsId, result.getId());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        // movieService.updateMovie deve lançar uma exception quando o Id nao existir
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = movieService.updateMovie(nonExistsId, movieDTO);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        // movieService.deleteMovie deve lançar uma exception quando o Id nao existir
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            movieService.deleteMovie(nonExistsId);
        });
    }

}