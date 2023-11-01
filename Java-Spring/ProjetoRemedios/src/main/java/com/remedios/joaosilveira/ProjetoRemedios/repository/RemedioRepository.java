package com.remedios.joaosilveira.ProjetoRemedios.repository;

import com.remedios.joaosilveira.ProjetoRemedios.remedio.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
    List<Remedio> findAllByAtivo(Boolean ativo);
}
