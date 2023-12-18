package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.dtos.UserDTO;
import com.joaosilveira.dscatalog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT new com.joaosilveira.dscatalog.dtos.UserDTO(obj) FROM User obj",
            countQuery = "SELECT COUNT(obj) FROM User obj JOIN obj.roles")
    Page<UserDTO> findAllPageable(Pageable pageable);

    // busca no banco de dados um Usuário passando um email como argumento
    User findByEmail(String email);

}
