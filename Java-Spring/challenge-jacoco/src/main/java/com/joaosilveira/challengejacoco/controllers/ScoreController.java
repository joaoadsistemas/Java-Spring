package com.joaosilveira.challengejacoco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaosilveira.challengejacoco.dto.MovieDTO;
import com.joaosilveira.challengejacoco.dto.ScoreDTO;
import com.joaosilveira.challengejacoco.services.ScoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {

    @Autowired
    private ScoreService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PutMapping
    public ResponseEntity<MovieDTO> saveScore(@Valid @RequestBody ScoreDTO dto) {
        MovieDTO movieDTO = service.saveScore(dto);
        return ResponseEntity.ok().body(movieDTO);
    }
}
