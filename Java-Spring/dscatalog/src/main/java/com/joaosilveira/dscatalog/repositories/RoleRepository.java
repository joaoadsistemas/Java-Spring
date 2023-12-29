package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.dtos.UserDTO;
import com.joaosilveira.dscatalog.entities.Role;
import com.joaosilveira.dscatalog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

}
