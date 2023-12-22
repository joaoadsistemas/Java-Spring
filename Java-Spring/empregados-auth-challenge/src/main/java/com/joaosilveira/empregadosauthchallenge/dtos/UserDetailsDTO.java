package com.joaosilveira.empregadosauthchallenge.dtos;

import com.joaosilveira.empregadosauthchallenge.entities.Role;
import com.joaosilveira.empregadosauthchallenge.entities.User;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsDTO {

    private Long id;

    @Email(message = "Favor entrar com um email válido")
    private String email;
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();


    public UserDetailsDTO() {

    }

    public UserDetailsDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserDetailsDTO(User entity) {
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
