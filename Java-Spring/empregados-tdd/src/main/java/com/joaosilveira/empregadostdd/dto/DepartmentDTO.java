package com.joaosilveira.empregadostdd.dto;

import com.joaosilveira.empregadostdd.entities.Department;

public class DepartmentDTO {

    private Long id;
    private String name;

    public DepartmentDTO() {

    }

    public DepartmentDTO(Department entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
