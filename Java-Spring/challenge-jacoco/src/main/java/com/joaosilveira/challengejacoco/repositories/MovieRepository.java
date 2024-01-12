package com.joaosilveira.challengejacoco.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joaosilveira.challengejacoco.entities.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query("SELECT obj FROM MovieEntity obj "
            + "WHERE UPPER(obj.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    Page<MovieEntity> searchByTitle(String title, Pageable pageable);
}
