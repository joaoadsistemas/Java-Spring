package com.joaosilveira.dscatalog.repositories;

import com.joaosilveira.dscatalog.entities.PasswordRecover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long> {

    // procura um token com base no token passado por argumento, e também vê se o instant do token é maior que o
    // passado por argumento também
    @Query("SELECT obj FROM PasswordRecover obj WHERE obj.token = :token AND obj.expiration > :now")
    List<PasswordRecover> searchValidTokens(String token, Instant now);

}
