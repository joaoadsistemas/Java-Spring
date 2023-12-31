package com.joaosilveira.challengemovieflix.repositories;

import com.joaosilveira.challengemovieflix.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByMovieId(Long movieId);
}

