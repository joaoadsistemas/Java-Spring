package com.joaosilveira.challengeauthsecurity.dto;

import com.joaosilveira.challengeauthsecurity.entities.Role;
import com.joaosilveira.challengeauthsecurity.entities.User;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String email;
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {

    }

    public UserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }


    public UserDTO(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        for (Role role: entity.getRoles()) {
            this.getRoles().add(new RoleDTO(role));
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}


