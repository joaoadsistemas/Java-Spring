package com.joaosilveira.mytestsintegrationunitary.dtos;

import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.projections.GenreProjection;
import com.joaosilveira.mytestsintegrationunitary.projections.MovieProjection;

import java.util.HashSet;
import java.util.Set;

public class GenreDTO {

    private Long id;
    private String genreName;

    private Set<MovieDTO> movies = new HashSet<>();

    public GenreDTO() {
    }

    public GenreDTO(Long id, String genreName) {
        this.id  = id;
        this.genreName = genreName;
    }

    public GenreDTO(Genre entity) {
        this.id = entity.getId();
        this.genreName = entity.getGenreName();

        for (Movie movie: entity.getMovies()
             ) {
            movies.add(new MovieDTO(movie));
        }
    }

    public GenreDTO(GenreProjection genreProjection) {
        this.id = genreProjection.getId();
        this.genreName = genreProjection.getGenreName();
    }

    public String getGenreName() {
        return genreName;
    }

    public Set<MovieDTO> getMovies() {
        return movies;
    }
}
