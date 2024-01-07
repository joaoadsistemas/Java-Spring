package com.joaosilveira.mytestsintegrationunitary.service;

import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.projections.MovieProjection;
import com.joaosilveira.mytestsintegrationunitary.repositories.MovieRepository;
import com.joaosilveira.mytestsintegrationunitary.services.MovieService;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    private Movie movie;

    private MovieDTO movieDTO;

    private Long existsId;

    private Long nonExistsId;
    private Long dependentId;
    private PageImpl<MovieDTO> page;


    @BeforeEach
    void setUp() throws Exception {

        movie = new Movie(1L, "Cars 2", "Lorem ipsum");
        movieDTO = new MovieDTO(1L, "Cars 2", "Lorem Ipsum");
        existsId = 1L;
        nonExistsId = 2L;
        page = new PageImpl<>(List.of(new MovieDTO(1L, "", "")));


        Mockito.when(movieRepository.findById(existsId)).thenReturn(Optional.of(movie));
        Mockito.doThrow(ResourceNotFoundException.class).when(movieRepository).findById(nonExistsId);

        Mockito.when(movieRepository.save(any())).thenReturn(movie);

        Mockito.when(movieRepository.getReferenceById(existsId)).thenReturn(movie);
        Mockito.doThrow(EntityNotFoundException.class).when(movieRepository).getReferenceById(nonExistsId);


        Mockito.when(movieRepository.existsById(dependentId)).thenReturn(true);
        Mockito.doThrow(DataIntegrityViolationException.class).when(movieRepository).deleteById(dependentId);

    }

    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() {
        MovieDTO result = movieService.findMovieById(existsId);
        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = movieService.findMovieById(nonExistsId);
        });

    }

    @Test
    public void insertShouldReturnMovieDTO() {

        MovieDTO result = movieService.insertMovie(movieDTO);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Cars 2", result.getMovieName());
    }

    @Test
    public void updateShouldReturnMovieDTOWhenIdExists() {
        MovieDTO result = movieService.updateMovie(existsId, movieDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(existsId, result.getId());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = movieService.updateMovie(nonExistsId, movieDTO);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            movieService.deleteMovie(nonExistsId);
        });
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionnWhenDependentId() {

        Assertions.assertThrows(DatabaseException.class, () -> {
            movieService.deleteMovie(dependentId);
        });
    }
}