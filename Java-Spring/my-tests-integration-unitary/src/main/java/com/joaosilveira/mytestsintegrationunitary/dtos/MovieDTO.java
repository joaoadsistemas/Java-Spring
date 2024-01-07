package com.joaosilveira.mytestsintegrationunitary.dtos;

import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.projections.GenreProjection;
import com.joaosilveira.mytestsintegrationunitary.projections.MovieProjection;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

public class MovieDTO {

    private Long id;
    private String movieName;

    private String description;

    private Set<GenreNoFilmDTO> genres = new HashSet<>();


    public MovieDTO() {

    }

    public MovieDTO(Long id, String movieName, String description) {
        this.id = id;
        this.movieName = movieName;
        this.description = description;
    }


    public MovieDTO(Movie entity) {
        this.id = entity.getId();
        this.movieName = entity.getMovieName();
        this.description = entity.getDescription();

        for (Genre genre: entity.getGenres()
             ) {
            genres.add(new GenreNoFilmDTO(genre));
        }
    }

    public MovieDTO(MovieProjection movieProjection) {
        this.id = movieProjection.getId();
        this.movieName = movieProjection.getMovieName();
        this.description = movieProjection.getDescription();

        for (GenreProjection genreProjection: movieProjection.getGenres()
             ) {
            this.genres.add(new GenreNoFilmDTO(genreProjection));
        }
    }

    public Long getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getDescription() {
        return description;
    }

    public Set<GenreNoFilmDTO> getGenres() {
        return genres;
    }
}
