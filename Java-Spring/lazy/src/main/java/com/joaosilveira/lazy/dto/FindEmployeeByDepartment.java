package com.joaosilveira.lazy.dto;

import com.joaosilveira.lazy.entities.Department;
import com.joaosilveira.lazy.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class FindEmployeeByDepartment {

    private DepartmentDTO department;

    private List<EmployeeMinDTO> employees = new ArrayList<>();


    public FindEmployeeByDepartment(Department entity) {
        this.department = new DepartmentDTO(entity);

        for (Employee e : entity.getEmployees()) {
            this.employees.add(new EmployeeMinDTO(e));
        }

    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public List<EmployeeMinDTO> getEmployees() {
        return employees;
    }
}
