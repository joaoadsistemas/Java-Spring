package com.joaosilveira.challengeauthsecurity.repositories;

import com.joaosilveira.challengeauthsecurity.dto.CityDTO;
import com.joaosilveira.challengeauthsecurity.entities.City;
import com.joaosilveira.challengeauthsecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT new com.joaosilveira.challengeauthsecurity.dto.CityDTO(obj) FROM City obj " +
            "ORDER BY obj.name")
    List<CityDTO> findAllByOrderByNomeAsc();
}
