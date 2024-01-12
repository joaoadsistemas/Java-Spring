package com.joaosilveira.challengejacoco.repositories;

import com.joaosilveira.challengejacoco.entities.MovieEntity;
import com.joaosilveira.challengejacoco.entities.ScoreEntity;
import com.joaosilveira.challengejacoco.entities.ScoreEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreRepository extends JpaRepository<ScoreEntity, ScoreEntityPK> {

}
