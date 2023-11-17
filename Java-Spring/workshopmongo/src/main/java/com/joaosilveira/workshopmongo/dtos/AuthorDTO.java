package com.joaosilveira.workshopmongo.dtos;

import com.joaosilveira.workshopmongo.entities.User;

public record AuthorDTO(
    String id,
    String nome
) {

    public AuthorDTO(User obj) {
        this(
                obj.getId(),
                obj.getEmail()
        );
    }

}
