package com.joaosilveira.challengemovieflix.controllers;


import com.joaosilveira.challengemovieflix.dto.MovieCardDTO;
import com.joaosilveira.challengemovieflix.dto.MovieDetailsDTO;
import com.joaosilveira.challengemovieflix.dto.ReviewDTO;
import com.joaosilveira.challengemovieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDetailsDTO> findById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(movieService.findById(id));
	}

	@PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
	@GetMapping()
	public ResponseEntity<Page<MovieCardDTO>> findPageable(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "genreId", defaultValue = "0") String genreId,
			Pageable pageable
	) {
		return ResponseEntity.ok(movieService.findAllPaged(name, genreId, pageable));
	}

	@PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> review(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(movieService.review(id));
	}


}
