package com.joaosilveira.salvarparaum.repositories;

import com.joaosilveira.salvarparaum.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
