package com.joaosilveira.crudchallange.repositories;

import com.joaosilveira.crudchallange.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
