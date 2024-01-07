package com.joaosilveira.mytestsintegrationunitary.repositories;

import com.joaosilveira.mytestsintegrationunitary.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
