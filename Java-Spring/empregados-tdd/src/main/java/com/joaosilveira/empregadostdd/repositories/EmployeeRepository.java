package com.joaosilveira.empregadostdd.repositories;

import com.joaosilveira.empregadostdd.dto.DepartmentDTO;
import com.joaosilveira.empregadostdd.dto.EmployeeDTO;
import com.joaosilveira.empregadostdd.entities.Department;
import com.joaosilveira.empregadostdd.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.joaosilveira.empregadostdd.dto.EmployeeDTO(obj) FROM Employee obj " +
            "ORDER BY obj.name")
    Page<EmployeeDTO> findAllPageable(Pageable pageable);
}
