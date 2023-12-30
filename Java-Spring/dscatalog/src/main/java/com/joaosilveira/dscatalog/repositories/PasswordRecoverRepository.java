package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.entities.PasswordRecover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long> {

}
