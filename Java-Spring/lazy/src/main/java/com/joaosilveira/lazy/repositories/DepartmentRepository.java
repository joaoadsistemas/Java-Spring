package com.joaosilveira.lazy.repositories;

import com.joaosilveira.lazy.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
