package com.joaosilveira.mytestsintegrationunitary.repositories;

import com.joaosilveira.mytestsintegrationunitary.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

}
