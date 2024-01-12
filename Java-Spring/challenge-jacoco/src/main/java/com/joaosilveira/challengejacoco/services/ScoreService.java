package com.joaosilveira.challengejacoco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaosilveira.challengejacoco.dto.MovieDTO;
import com.joaosilveira.challengejacoco.dto.ScoreDTO;
import com.joaosilveira.challengejacoco.entities.MovieEntity;
import com.joaosilveira.challengejacoco.entities.ScoreEntity;
import com.joaosilveira.challengejacoco.entities.UserEntity;
import com.joaosilveira.challengejacoco.repositories.MovieRepository;
import com.joaosilveira.challengejacoco.repositories.ScoreRepository;
import com.joaosilveira.challengejacoco.services.exceptions.ResourceNotFoundException;

@Service
public class ScoreService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto) {

        UserEntity user = userService.authenticated();

        MovieEntity movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        ScoreEntity score = new ScoreEntity();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);

        double sum = 0.0;
        for (ScoreEntity s : movie.getScores()) {
            sum = sum + s.getValue();
        }

        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        movie = movieRepository.save(movie);

        return new MovieDTO(movie);
    }
}