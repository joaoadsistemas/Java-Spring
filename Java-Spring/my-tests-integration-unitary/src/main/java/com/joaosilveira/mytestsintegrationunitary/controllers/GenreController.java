package com.joaosilveira.mytestsintegrationunitary.controllers;


import com.joaosilveira.mytestsintegrationunitary.dtos.GenreDTO;
import com.joaosilveira.mytestsintegrationunitary.dtos.GenreNoFilmDTO;
import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import com.joaosilveira.mytestsintegrationunitary.services.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;


    @GetMapping
    public ResponseEntity<List<GenreNoFilmDTO>> findAllGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreNoFilmDTO> findGenreById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(genreService.findGenreById(id));
    }

    @PostMapping
    public ResponseEntity<GenreNoFilmDTO> insertGenre(@Valid @RequestBody GenreNoFilmDTO dto) {
        dto = genreService.insertGenre(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreNoFilmDTO> updateGenre(@PathVariable(value = "id") Long id, @Valid @RequestBody GenreNoFilmDTO dto) {
        return ResponseEntity.ok(genreService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
