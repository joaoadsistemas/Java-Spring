package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value = "SELECT EMPREGADOS.CPF, EMPREGADOS.ENOME, DEPARTAMENTOS.DNOME " +
            "FROM EMPREGADOS " +
            "INNER JOIN DEPARTAMENTOS ON EMPREGADOS.DNUMERO = DEPARTAMENTOS.DNUMERO " +
            "WHERE EMPREGADOS.CPF NOT IN( " +
            "SELECT EMPREGADOS.CPF " +
            "FROM EMPREGADOS " +
            "INNER JOIN TRABALHA ON TRABALHA.CPF_EMP = EMPREGADOS.CPF) " +
            "ORDER BY EMPREGADOS.CPF")
    List<EmpregadoDeptProjection> search1();

}
