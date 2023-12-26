package com.joaosilveira.challengeauthsecurity.services;


import com.joaosilveira.challengeauthsecurity.dto.EventDTO;
import com.joaosilveira.challengeauthsecurity.entities.Event;
import com.joaosilveira.challengeauthsecurity.repositories.CityRepository;
import com.joaosilveira.challengeauthsecurity.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;


    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPageable(Pageable pageable) {
        Page<EventDTO> result = eventRepository.findAllPageable(pageable);
        return result;
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        copyDtoToEntity(dto, event);
        event = eventRepository.save(event);
        return new EventDTO(event);
    }

    private void copyDtoToEntity(EventDTO dto, Event event) {
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());
        event.setCity(cityRepository.getReferenceById(dto.getCityId()));
        event.setDate(dto.getDate());
    }
}
