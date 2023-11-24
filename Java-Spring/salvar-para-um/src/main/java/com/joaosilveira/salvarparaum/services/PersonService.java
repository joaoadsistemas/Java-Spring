package com.joaosilveira.salvarparaum.services;

import com.joaosilveira.salvarparaum.dto.PersonDepartmentDTO;
import com.joaosilveira.salvarparaum.entities.Department;
import com.joaosilveira.salvarparaum.entities.Person;
import com.joaosilveira.salvarparaum.repositories.DepartmentRepository;
import com.joaosilveira.salvarparaum.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public PersonDepartmentDTO insert(PersonDepartmentDTO dto) {
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        Department department = departmentRepository.getReferenceById(dto.getDepartment().getId());

        entity.setDepartment(department);

        entity = personRepository.save(entity);

        return new PersonDepartmentDTO(entity);
    }


}
