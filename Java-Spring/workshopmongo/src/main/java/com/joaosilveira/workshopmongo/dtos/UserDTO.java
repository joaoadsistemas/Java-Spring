package com.joaosilveira.workshopmongo.dtos;

import com.joaosilveira.workshopmongo.entities.User;

public record UserDTO(
        String id,
        String name,
        String email
) {

    public UserDTO(User obj) {
        this(
                obj.getId(),
                obj.getName(),
                obj.getEmail()
        );
    }

}
