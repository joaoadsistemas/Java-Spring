package com.joaosilveira.empregadosauthchallenge.services;

import com.joaosilveira.empregadosauthchallenge.dtos.DepartmentDTO;
import com.joaosilveira.empregadosauthchallenge.entities.Department;
import com.joaosilveira.empregadosauthchallenge.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<DepartmentDTO> findAll() {
        List<Department> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new DepartmentDTO(x)).toList();
    }
}
