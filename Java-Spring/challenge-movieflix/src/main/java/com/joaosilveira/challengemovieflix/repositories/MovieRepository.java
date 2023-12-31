package com.joaosilveira.challengemovieflix.repositories;

import com.joaosilveira.challengemovieflix.entities.Movie;
import com.joaosilveira.challengemovieflix.projections.GenreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = """
    	SELECT * FROM (
	    SELECT DISTINCT tb_movie.id, tb_movie.title
	    FROM tb_movie
	    WHERE (:genreIds IS NULL OR tb_movie.genre_id IN :genreIds)
	    AND (LOWER(tb_movie.title) LIKE LOWER(CONCAT('%',:title,'%')))
	    ORDER BY tb_movie.title
        ) AS tb_result
	    """,
            countQuery = """
	     SELECT COUNT(*) FROM (
         SELECT DISTINCT tb_movie.id, tb_movie.title
         FROM tb_movie
         LEFT JOIN tb_genre ON tb_movie.genre_id = tb_genre.id
         WHERE (:genreIds IS NULL OR tb_genre.id IN :genreIds)
         AND (LOWER(tb_movie.title) LIKE LOWER(CONCAT('%',:title,'%')))
         ) AS tb_result
	    """)
    Page<GenreProjection> searchMovies(List<Long> genreIds, String title, Pageable pageable);

    @Query(value = "SELECT obj FROM Movie obj JOIN FETCH obj.genre " +
            "WHERE obj.id IN :moviesIds",
            countQuery = "SELECT COUNT(obj) FROM Movie obj JOIN obj.genre")
    List<Movie> searchMoviesWithGenres(List<Long> moviesIds);
}
