package com.joaosilveira.challengejacoco.services;

import com.joaosilveira.challengejacoco.dto.MovieDTO;
import com.joaosilveira.challengejacoco.dto.ScoreDTO;
import com.joaosilveira.challengejacoco.entities.MovieEntity;
import com.joaosilveira.challengejacoco.entities.ScoreEntity;
import com.joaosilveira.challengejacoco.entities.UserEntity;
import com.joaosilveira.challengejacoco.repositories.MovieRepository;
import com.joaosilveira.challengejacoco.repositories.ScoreRepository;
import com.joaosilveira.challengejacoco.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.challengejacoco.tests.MovieFactory;
import com.joaosilveira.challengejacoco.tests.ScoreFactory;
import com.joaosilveira.challengejacoco.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {

    @InjectMocks
    private ScoreService service;

    @Mock
    private UserService userService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ScoreRepository scoreRepository;

    UserEntity userEntity;

    MovieEntity movieEntity;
    ScoreEntity scoreEntity;
    ScoreDTO scoreDTO;

    private Long existingId, nonExistingId;

    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;
        nonExistingId = 2L;
        userEntity = UserFactory.createUserEntity();
        movieEntity = MovieFactory.createMovieEntity();
        scoreEntity = ScoreFactory.createScoreEntity();
        scoreDTO = ScoreFactory.createScoreDTO();


        Mockito.when(userService.authenticated()).thenReturn(userEntity);
        Mockito.when(movieRepository.findById(existingId)).thenReturn(Optional.of(movieEntity));
        Mockito.when(movieRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        Mockito.when(scoreRepository.saveAndFlush(any())).thenReturn(scoreEntity);
        Mockito.when(movieRepository.save(any())).thenReturn(movieEntity);

    }


    @Test
    public void saveScoreShouldReturnMovieDTO() {
        MovieDTO result = service.saveScore(scoreDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getCount(), movieEntity.getCount());

    }

    @Test
    public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {

        scoreDTO = new ScoreDTO(nonExistingId, 2.0);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            MovieDTO result = service.saveScore(scoreDTO);

        });

    }
}