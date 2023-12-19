package com.joaosilveira.authorizationbaseproject.dto;

import com.joaosilveira.authorizationbaseproject.entities.Role;
import com.joaosilveira.authorizationbaseproject.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private String email;
    private String password;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {

    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(User entity) {
        this.email = entity.getEmail();
        this.password = entity.getPassword();

        for (Role role: entity.getRoles()) {
            roles.add(new RoleDTO(role));
        }
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
