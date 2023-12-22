package com.joaosilveira.empregadosauthchallenge.repositories;

import com.joaosilveira.empregadosauthchallenge.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
