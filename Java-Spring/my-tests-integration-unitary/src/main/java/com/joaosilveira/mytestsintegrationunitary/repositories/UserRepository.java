package com.joaosilveira.mytestsintegrationunitary.repositories;

import com.joaosilveira.mytestsintegrationunitary.dtos.UserDTO;
import com.joaosilveira.mytestsintegrationunitary.dtos.UserInsertDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT new com.joaosilveira.mytestsintegrationunitary.dtos.UserDTO(obj) FROM User obj",
            countQuery = "SELECT COUNT(obj) FROM User obj JOIN obj.roles")
    Page<UserDTO> findAllPageable(Pageable pageable);

    // busca no banco de dados um Usuário passando um email como argumento
    User findByEmail(String email);

    @Query(value = "SELECT new com.joaosilveira.mytestsintegrationunitary.dtos.UserInsertDTO(obj) FROM User obj " +
            "WHERE obj.email = :email",
            countQuery = "SELECT COUNT(obj) FROM User obj join obj.roles")
    List<UserInsertDTO> searchUserAndRolesByEmail(String email);

}
