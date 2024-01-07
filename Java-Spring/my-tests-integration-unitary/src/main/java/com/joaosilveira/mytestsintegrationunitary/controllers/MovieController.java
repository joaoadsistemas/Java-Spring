package com.joaosilveira.mytestsintegrationunitary.controllers;

import com.joaosilveira.mytestsintegrationunitary.dtos.MovieDTO;
import com.joaosilveira.mytestsintegrationunitary.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAllMoviesPage(Pageable pageable) {
        return ResponseEntity.ok(movieService.findAllMoviesPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findMovieById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    @PostMapping
    public ResponseEntity<MovieDTO> insertMovie(@Valid @RequestBody MovieDTO dto) {
        dto = movieService.insertMovie(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable(name = "id") Long id,
                                                @Valid @RequestBody MovieDTO dto) {
        return ResponseEntity.ok(movieService.updateMovie(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable(name = "id") Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
