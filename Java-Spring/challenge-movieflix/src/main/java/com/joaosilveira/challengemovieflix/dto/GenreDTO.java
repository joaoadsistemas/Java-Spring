package com.joaosilveira.challengemovieflix.dto;

import com.joaosilveira.challengemovieflix.entities.Genre;

public class GenreDTO {

    private Long id;
    private String name;

    public GenreDTO(Genre entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
