package com.joaosilveira.challengemovieflix.repositories;

import com.joaosilveira.challengemovieflix.entities.Genre;
import com.joaosilveira.challengemovieflix.entities.Movie;
import com.joaosilveira.challengemovieflix.projections.GenreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
