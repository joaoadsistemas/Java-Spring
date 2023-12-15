package com.joaosilveira.empregadostdd.services;

import com.joaosilveira.empregadostdd.dto.DepartmentDTO;
import com.joaosilveira.empregadostdd.entities.Department;
import com.joaosilveira.empregadostdd.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findALlByName() {
        List<DepartmentDTO> result = departmentRepository.findAllByName();
        return result;
    }
}
