package com.joaosilveira.workshopmongo.repositories;

import com.joaosilveira.workshopmongo.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<User, Long> {
}
