package com.joaosilveira.mytestsintegrationunitary.services;


import com.joaosilveira.mytestsintegrationunitary.dtos.GenreNoFilmDTO;
import com.joaosilveira.mytestsintegrationunitary.repositories.GenreRepository;
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

import java.util.List;

@SpringBootTest
@Transactional
public class GenreServiceTestsIT {

    @Autowired
    private GenreService genreService;

    private GenreNoFilmDTO genreNoFilmDTO;

    private Long existsId;
    private Long nonExistsId;

    @BeforeEach
    void setUp() throws Exception {
        existsId = 1L;
        nonExistsId = 1000L;

        genreNoFilmDTO = new GenreNoFilmDTO(1L, "Horror");
    }

    @Test
    public void findAllShouldReturnListOfGenreNoFilmDTO() {

        List<GenreNoFilmDTO> result = genreService.findAllGenres();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(genreNoFilmDTO.getId(), result.get(0).getId());

    }

    @Test
    public void findByIdShouldReturnGenreNoFilmDTOWhenIdExists() {

        GenreNoFilmDTO result = genreService.findGenreById(existsId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(genreNoFilmDTO.getId(), result.getId());

    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            GenreNoFilmDTO result = genreService.findGenreById(nonExistsId);
        });
    }

    @Test
    public void insertShouldReturnGenreNoFilmDTO() {

        GenreNoFilmDTO result = genreService.insertGenre(genreNoFilmDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(4, result.getId());
    }

    @Test
    public void updateShouldReturnGenreNoFilmDTOWhenIdExistsAndValidData() {

        GenreNoFilmDTO result = genreService.update(existsId, genreNoFilmDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(genreNoFilmDTO.getId(), result.getId());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExistsAndValidData() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            GenreNoFilmDTO result = genreService.update(nonExistsId, genreNoFilmDTO);

        });

    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            genreService.deleteById(nonExistsId);
        });

    }

}
