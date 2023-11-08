package com.joaosilveira.workshopmongo.repositories;

import com.joaosilveira.workshopmongo.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findById(String id);
    Optional<User> deleteById(String id);
}
