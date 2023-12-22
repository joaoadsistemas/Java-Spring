package com.joaosilveira.empregadosauthchallenge.repositories;

import com.joaosilveira.empregadosauthchallenge.dtos.UserDetailsDTO;
import com.joaosilveira.empregadosauthchallenge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserReporitory extends JpaRepository<User, Long> {


    @Query(value = "SELECT new com.joaosilveira.empregadosauthchallenge.dtos.UserDetailsDTO(obj) FROM User obj " +
            "WHERE obj.email = :email",
            countQuery = "SELECT COUNT(obj) FROM User obj join obj.roles")
    List<UserDetailsDTO> searchUserAndRolesByEmail(String email);
}
