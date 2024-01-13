package com.joaosilveira.challengejacoco.services;

import com.joaosilveira.challengejacoco.dto.MovieDTO;
import com.joaosilveira.challengejacoco.entities.MovieEntity;
import com.joaosilveira.challengejacoco.repositories.MovieRepository;
import com.joaosilveira.challengejacoco.services.exceptions.DatabaseException;
import com.joaosilveira.challengejacoco.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.challengejacoco.tests.MovieFactory;
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
public class MovieServiceTests {

    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository movieRepository;


    private PageImpl<MovieEntity> page;
    private MovieEntity movieEntity;
    private MovieDTO movieDTO;
    private Long existId, nonExistId, dependentId;


    @BeforeEach
    void setUp() throws Exception {

        existId = 1L;
        nonExistId = 2L;
        dependentId = 3L;
        movieEntity = MovieFactory.createMovieEntity();
        page = new PageImpl<>(List.of(movieEntity));
        movieDTO = MovieFactory.createMovieDTO();

        Mockito.when(movieRepository.searchByTitle(any(), any())).thenReturn(page);

        Mockito.when(movieRepository.findById(existId)).thenReturn(Optional.of(movieEntity));
        Mockito.when(movieRepository.findById(nonExistId)).thenReturn(Optional.empty());

        Mockito.when(movieRepository.save(any())).thenReturn(movieEntity);

        Mockito.when(movieRepository.getReferenceById(existId)).thenReturn(movieEntity);
        Mockito.when(movieRepository.getReferenceById(nonExistId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(movieRepository.existsById(existId)).thenReturn(true);
        Mockito.when(movieRepository.existsById(dependentId)).thenReturn(true);
        Mockito.when(movieRepository.existsById(nonExistId)).thenReturn(false);

        Mockito.doNothing().when(movieRepository).deleteById(existId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(movieRepository).deleteById(dependentId);

    }

    @Test
    public void findAllShouldReturnPagedMovieDTO() {
        Pageable pageable = PageRequest.of(0, 12);
        String name = "Titanic";

        Page<MovieDTO> result = service.findAll(name, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.iterator().next().getTitle(), movieEntity.getTitle());
        Assertions.assertEquals(result.getSize(), 1);

    }

    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() {

        MovieDTO result = service.findById(existId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existId);

    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = service.findById(nonExistId);
        });

    }

    @Test
    public void insertShouldReturnMovieDTO() {

        MovieDTO result = service.insert(movieDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), movieDTO.getId());

    }

    @Test
    public void updateShouldReturnMovieDTOWhenIdExists() {

        MovieDTO result = service.update(existId, movieDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), movieDTO.getId());

    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = service.update(nonExistId, movieDTO);
        });

    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            service.delete(existId);
        });

    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistId);
        });

    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            service.delete(dependentId);
        });
    }
}
