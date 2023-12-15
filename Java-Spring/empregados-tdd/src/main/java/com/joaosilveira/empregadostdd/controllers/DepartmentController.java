package com.joaosilveira.empregadostdd.controllers;

import com.joaosilveira.empregadostdd.dto.DepartmentDTO;
import com.joaosilveira.empregadostdd.entities.Department;
import com.joaosilveira.empregadostdd.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        return ResponseEntity.ok(departmentService.findALlByName());
    }

}
