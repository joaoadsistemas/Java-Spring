package com.joaosilveira.lazy.controllers;

import com.joaosilveira.lazy.dto.EmployeeDepartmentDTO;
import com.joaosilveira.lazy.dto.EmployeeMinDTO;
import com.joaosilveira.lazy.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping(value = "/{id}/min")
	public ResponseEntity<EmployeeMinDTO> findByIdMin(@PathVariable Long id) {
		EmployeeMinDTO obj = service.findByIdMin(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDepartmentDTO> findByIdWithDepartment(@PathVariable Long id) {
		EmployeeDepartmentDTO obj = service.findByIdWithDepartment(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDepartmentDTO>> findEmployeesWithDepartments() {
		List<EmployeeDepartmentDTO> list = service.findEmployeesWithDepartments();
		return ResponseEntity.ok(list);
	}
}
