package com.joaosilveira.empregadostdd.services;

import com.joaosilveira.empregadostdd.dto.EmployeeDTO;
import com.joaosilveira.empregadostdd.entities.Department;
import com.joaosilveira.empregadostdd.entities.Employee;
import com.joaosilveira.empregadostdd.repositories.DepartmentRepository;
import com.joaosilveira.empregadostdd.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Page<EmployeeDTO> findAll(Pageable pageable) {
        Page<EmployeeDTO> result = employeeRepository.findAllPageable(pageable);
        return result;
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        Employee employee = new Employee();
        copyDtoToEntity(dto, employee);
        employee = employeeRepository.save(employee);
        return new EmployeeDTO(employee);
    }

    public void copyDtoToEntity(EmployeeDTO dto, Employee entity) {
           entity.setName(dto.getName());
           entity.setEmail(dto.getEmail());
           entity.setDepartment(new Department(dto.getDepartmentId(), null));

    }
}
