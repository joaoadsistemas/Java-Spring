package com.joaosilveira.mytestsintegrationunitary.repositories;

import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.projections.MovieProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT obj FROM Movie obj JOIN FETCH obj.genres",
            countQuery = "SELECT COUNT(obj) FROM Movie obj JOIN obj.genres")
    Page<MovieProjection> findPageable(Pageable pageable);
}
