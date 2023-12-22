package com.joaosilveira.empregadosauthchallenge.repositories;

import com.joaosilveira.empregadosauthchallenge.entities.Department;
import com.joaosilveira.empregadosauthchallenge.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
