package com.joaosilveira.challengeauthsecurity.repositories;

import com.joaosilveira.challengeauthsecurity.dto.UserDTO;
import com.joaosilveira.challengeauthsecurity.dto.UserInsertDTO;
import com.joaosilveira.challengeauthsecurity.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT new com.joaosilveira.challengeauthsecurity.dto.UserDTO(obj) FROM User obj",
            countQuery = "SELECT COUNT(obj) FROM User obj JOIN obj.roles")
    Page<UserDTO> findAllPageable(Pageable pageable);

    // busca no banco de dados um Usuário passando um email como argumento
    User findByEmail(String email);

    @Query(value = "SELECT new com.joaosilveira.challengeauthsecurity.dto.UserInsertDTO(obj) FROM User obj " +
            "WHERE obj.email = :email",
            countQuery = "SELECT COUNT(obj) FROM User obj join obj.roles")
    List<UserInsertDTO> searchUserAndRolesByEmail(String email);

}
