package com.joaosilveira.challengemovieflix.services;

import com.joaosilveira.challengemovieflix.dto.ReviewDTO;
import com.joaosilveira.challengemovieflix.entities.Review;
import com.joaosilveira.challengemovieflix.repositories.MovieRepository;
import com.joaosilveira.challengemovieflix.repositories.ReviewRepository;
import com.joaosilveira.challengemovieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        copyDtoToEntity(dto, entity);
        entity = reviewRepository.save(entity);
        return new ReviewDTO(entity);
    }

    private void copyDtoToEntity(ReviewDTO dto, Review review) {
        review.setId(dto.getId());
        review.setText(dto.getText());
        review.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
        review.setUser(authService.authenticated());
    }
}
