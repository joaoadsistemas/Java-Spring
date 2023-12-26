package com.joaosilveira.challengeauthsecurity.controllers;

import com.joaosilveira.challengeauthsecurity.dto.CityDTO;
import com.joaosilveira.challengeauthsecurity.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping()
    public ResponseEntity<List<CityDTO>> findAll() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO dto) {
        dto = cityService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


}
