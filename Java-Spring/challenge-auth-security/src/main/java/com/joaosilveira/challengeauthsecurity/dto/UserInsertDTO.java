package com.joaosilveira.challengeauthsecurity.dto;

import com.joaosilveira.challengeauthsecurity.entities.Role;
import com.joaosilveira.challengeauthsecurity.entities.User;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class UserInsertDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String email;

    private String password;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserInsertDTO() {

    }

    public UserInsertDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }


    public UserInsertDTO(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
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

    public String getPassword() {
        return password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}


