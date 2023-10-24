package com.joaosilveira.demo.repository;

import com.joaosilveira.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
