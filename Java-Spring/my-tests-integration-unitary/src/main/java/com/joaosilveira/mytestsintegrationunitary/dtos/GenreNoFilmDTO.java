package com.joaosilveira.mytestsintegrationunitary.dtos;

import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import com.joaosilveira.mytestsintegrationunitary.entities.Movie;
import com.joaosilveira.mytestsintegrationunitary.projections.GenreProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class GenreNoFilmDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, message = "Campo deve haver no mínimo 3 caracteres")
    private String genreName;

    public GenreNoFilmDTO() {
    }

    public GenreNoFilmDTO(Long id, String genreName) {
        this.id  = id;
        this.genreName = genreName;
    }

    public GenreNoFilmDTO(Genre entity) {
        this.id = entity.getId();
        this.genreName = entity.getGenreName();
    }

    public GenreNoFilmDTO(GenreProjection genreProjection) {
        this.id = genreProjection.getId();
        this.genreName = genreProjection.getGenreName();
    }

    public String getGenreName() {
        return genreName;
    }

    public Long getId() {
        return id;
    }
}
