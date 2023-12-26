package com.joaosilveira.challengeauthsecurity.repositories;

import com.joaosilveira.challengeauthsecurity.dto.EventDTO;
import com.joaosilveira.challengeauthsecurity.entities.City;
import com.joaosilveira.challengeauthsecurity.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {


    @Query(value = "SELECT new com.joaosilveira.challengeauthsecurity.dto.EventDTO(obj) " +
            "FROM Event obj",
            countQuery = "SELECT COUNT(obj) FROM Event obj JOIN obj.city")
    Page<EventDTO> findAllPageable(Pageable pageable);
}
