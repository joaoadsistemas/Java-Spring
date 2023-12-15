package com.joaosilveira.challengereventcity.controllers;

import com.joaosilveira.challengereventcity.dto.EventDTO;
import com.joaosilveira.challengereventcity.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable(value = "id") Long id, @RequestBody EventDTO dto) {
        return ResponseEntity.ok(eventService.update(id, dto));
    }

}
