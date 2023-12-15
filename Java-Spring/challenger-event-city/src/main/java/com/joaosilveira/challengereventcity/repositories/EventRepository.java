package com.joaosilveira.challengereventcity.repositories;

import com.joaosilveira.challengereventcity.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
