package com.joaosilveira.lazy.controllers;

import com.joaosilveira.lazy.dto.DepartmentDTO;
import com.joaosilveira.lazy.dto.EmployeeMinDTO;
import com.joaosilveira.lazy.dto.FindEmployeeByDepartment;
import com.joaosilveira.lazy.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id) {
		DepartmentDTO obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping(value = "/{id}/employees")
	public ResponseEntity<FindEmployeeByDepartment> findEmployeesByDepartment(@PathVariable Long id) {
		return ResponseEntity.ok(service.findEmployeesByDepartment(id));
	}


}
