package com.joaosilveira.demo.services;

import com.joaosilveira.demo.data.vo.v1.PersonVO;
import com.joaosilveira.demo.mapper.DozerMapper;
import com.joaosilveira.demo.model.Person;
import com.joaosilveira.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<PersonVO> findAll() {

        logger.info("Finding All people");

        return DozerMapper.parseListObjects(personRepository.findAll(),PersonVO.class);
    }



    public PersonVO findById(Long id) {
        logger.info("Finding one person");

        var person = personRepository.findById(id);

        return DozerMapper.parseObject(person, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;

    }

    public PersonVO update(PersonVO person) {
        logger.info("Creating one person!");

        var entityPerson = personRepository.findById(person.getId());

        var entity = entityPerson.get();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        System.out.println(person.getAdress());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = personRepository.findById(id);
        personRepository.delete(entity.get());

    }


}
