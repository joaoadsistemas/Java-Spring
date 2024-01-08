package com.joaosilveira.mytestsintegrationunitary.services;


import com.joaosilveira.mytestsintegrationunitary.dtos.GenreNoFilmDTO;
import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.repositories.GenreRepository;
import com.joaosilveira.mytestsintegrationunitary.repositories.MovieRepository;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.DatabaseException;
import com.joaosilveira.mytestsintegrationunitary.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

// usado para testes unitários
@ExtendWith(SpringExtension.class)
public class GenreServiceTests {

    // injeta um mock do movieService
    @InjectMocks
    private GenreService genreService;

    // cria um mock do movieRepository
    @Mock
    private GenreRepository genreRepository;

    private Genre genre;

    private GenreNoFilmDTO genreNoFilmDTO;

    private Long existsId, nonExistsId, dependentId;

    @BeforeEach
    void setUp() throws Exception {

        existsId = 1L;
        nonExistsId = 2L;
        dependentId = 3L;

        genre = new Genre(1L, "Horror");
        genreNoFilmDTO = new GenreNoFilmDTO(1L, "Horror");

        Mockito.when(genreRepository.findAll()).thenReturn(List.of(genre));

        Mockito.when(genreRepository.findById(existsId)).thenReturn(Optional.of(genre));
        Mockito.doThrow(ResourceNotFoundException.class).when(genreRepository).findById(nonExistsId);

        Mockito.when(genreRepository.save(any())).thenReturn(genre);

        Mockito.when(genreRepository.getReferenceById(existsId)).thenReturn(genre);
        Mockito.doThrow(EntityNotFoundException.class).when(genreRepository).getReferenceById(nonExistsId);

        Mockito.doNothing().when(genreRepository).deleteById(existsId);
        Mockito.doThrow(ResourceNotFoundException.class).when(genreRepository).deleteById(nonExistsId);

        // quando o genreRepository.existsById for chamado, ele deverá retornar true quando um id dependente for passado
        Mockito.when(genreRepository.existsById(dependentId)).thenReturn(true);
        Mockito.doThrow(DatabaseException.class).when(genreRepository).deleteById(dependentId);
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
        Assertions.assertEquals(genreNoFilmDTO.getId(), result.getId());
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

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            genreService.deleteById(dependentId);
        });
    }

}
