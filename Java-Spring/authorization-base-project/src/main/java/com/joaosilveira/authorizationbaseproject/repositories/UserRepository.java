package com.joaosilveira.authorizationbaseproject.repositories;

import com.joaosilveira.authorizationbaseproject.dto.UserDTO;
import com.joaosilveira.authorizationbaseproject.entities.Product;
import com.joaosilveira.authorizationbaseproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT new com.joaosilveira.authorizationbaseproject.dto.UserDTO(obj) FROM User obj " +
            "WHERE obj.email = :email",
        countQuery = "SELECT COUNT(obj) FROM User obj join obj.roles")
    List<UserDTO> searchUserAndRolesByEmail(String email);

}
