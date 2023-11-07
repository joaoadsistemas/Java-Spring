package com.joaosilveira.projectOrders.repositories;

import com.joaosilveira.projectOrders.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
