package com.joaosilveira.mytestsintegrationunitary.service;

import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.repositories.MovieRepository;
import com.joaosilveira.mytestsintegrationunitary.services.MovieService;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MovieServiceTestsIT {

    @Autowired
    private MovieService service;

    @Autowired
    private MovieRepository repository;

    private Long existsId;
    private Long nonExistsId;
    private Long dependentId;

    @BeforeEach
    void setUp() throws Exception {
        existsId = 1L;
        nonExistsId = 1000L;
        dependentId = 3L;
    }


    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() {

        MovieDTO result = service.findMovieById(existsId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = service.findMovieById(nonExistsId);
        });

    }

    @Test
    public void insertShouldReturnMovieDTO() {

        MovieDTO dto = new MovieDTO(1L, "Cars 2", "Lorem");

        MovieDTO result = service.insertMovie(dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto.getId(), result.getId());
    }

    @Test
    public void updateShouldReturnMovieDTOWhenIdExists() {

        MovieDTO dto = new MovieDTO(1L, "Cars 2", "Lorem");

        MovieDTO result = service.updateMovie(existsId, dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto.getId(), result.getId());

    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {

        MovieDTO dto = new MovieDTO(1L, "Cars 2", "Lorem");

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            MovieDTO result = service.updateMovie(nonExistsId, dto);
        });

    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteMovie(nonExistsId);
        });

    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {

        Assertions.assertThrows(DatabaseException.class, () -> {
            service.deleteMovie(dependentId);
        });

    }

}
