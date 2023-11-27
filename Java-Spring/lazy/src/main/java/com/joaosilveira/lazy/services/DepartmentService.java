package com.joaosilveira.lazy.services;

import com.joaosilveira.lazy.dto.DepartmentDTO;
import com.joaosilveira.lazy.dto.EmployeeMinDTO;
import com.joaosilveira.lazy.dto.FindEmployeeByDepartment;
import com.joaosilveira.lazy.entities.Department;
import com.joaosilveira.lazy.entities.Employee;
import com.joaosilveira.lazy.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;

	@Transactional(readOnly = true)
	public DepartmentDTO findById(Long id) {
		Optional<Department> result = repository.findById(id);
		return new DepartmentDTO(result.get());
	}

	@Transactional(readOnly = true)
	public FindEmployeeByDepartment findEmployeesByDepartment(Long id) {
		Optional<Department> result = repository.findById(id);
		return new FindEmployeeByDepartment(result.get());
	}
}
