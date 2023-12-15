package com.joaosilveira.empregadostdd.controllers;

import com.joaosilveira.empregadostdd.dto.DepartmentDTO;
import com.joaosilveira.empregadostdd.dto.EmployeeDTO;
import com.joaosilveira.empregadostdd.services.DepartmentService;
import com.joaosilveira.empregadostdd.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(employeeService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO dto) {
        dto = employeeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
