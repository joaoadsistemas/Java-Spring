package com.joaosilveira.demo.services;

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

    public List<Person> findAll() {

        logger.info("Finding All people");

        return personRepository.findAll();
    }



    public Person findById(Long id) {
        logger.info("Finding one person");

        var person = personRepository.findById(id);

        return person.get();
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Creating one person!");

        var entityPerson = personRepository.findById(person.getId());

        var entity = entityPerson.get();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        System.out.println(person.getAdress());

        return personRepository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = personRepository.findById(id);
        personRepository.delete(entity.get());

    }


}
