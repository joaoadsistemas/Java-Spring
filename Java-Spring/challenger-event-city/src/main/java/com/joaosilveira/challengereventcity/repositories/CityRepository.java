package com.joaosilveira.challengereventcity.repositories;

import com.joaosilveira.challengereventcity.dto.CityDTO;
import com.joaosilveira.challengereventcity.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT new com.joaosilveira.challengereventcity.dto.CityDTO(obj) FROM City obj " +
            "ORDER BY obj.name")
    List<CityDTO> findAllByName();
}
