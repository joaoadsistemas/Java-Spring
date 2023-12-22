package com.joaosilveira.empregadosauthchallenge.dtos;

import com.joaosilveira.empregadosauthchallenge.entities.Role;

public class RoleDTO {

    private Long id;
    private String authority;

    public RoleDTO() {

    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }
}
