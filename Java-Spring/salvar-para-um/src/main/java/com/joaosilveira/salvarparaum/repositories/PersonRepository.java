package com.joaosilveira.salvarparaum.repositories;

import com.joaosilveira.salvarparaum.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
