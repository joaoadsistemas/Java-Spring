package com.joaosilveira.challengereventcity.service;

import com.joaosilveira.challengereventcity.dto.EventDTO;
import com.joaosilveira.challengereventcity.entities.City;
import com.joaosilveira.challengereventcity.entities.Event;
import com.joaosilveira.challengereventcity.repositories.EventRepository;
import com.joaosilveira.challengereventcity.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event event = eventRepository.getReferenceById(id);
            copyDtoToEntity(dto, event);
            event = eventRepository.save(event);
            return new EventDTO(event);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {
        entity.setName(dto.getName());
        entity.setCity(new City(dto.getCityId(), null));
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
    }


}
