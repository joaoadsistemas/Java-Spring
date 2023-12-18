package com.joaosilveira.dscatalog.dtos;

import com.joaosilveira.dscatalog.entities.Role;
import com.joaosilveira.dscatalog.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String firstName;
    private String lastName;

    @Email(message = "Favor entrar com um email válido")
    private String email;
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {

    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDTO(User entity) {

        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();

        for (Role role: entity.getRoles()) {
            this.getRoles().add(new RoleDTO(role));
        }
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
