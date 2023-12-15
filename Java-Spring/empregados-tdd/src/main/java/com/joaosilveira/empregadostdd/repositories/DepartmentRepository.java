package com.joaosilveira.empregadostdd.repositories;

import com.joaosilveira.empregadostdd.dto.DepartmentDTO;
import com.joaosilveira.empregadostdd.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT new com.joaosilveira.empregadostdd.dto.DepartmentDTO(obj) FROM Department obj " +
            "ORDER BY obj.name")
    List<DepartmentDTO> findAllByName();

}
