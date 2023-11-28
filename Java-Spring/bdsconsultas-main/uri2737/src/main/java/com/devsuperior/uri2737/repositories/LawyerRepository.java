package com.devsuperior.uri2737.repositories;

import com.devsuperior.uri2737.projections.LawyerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2737.entities.Lawyer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    @Query(nativeQuery = true, value = "(SELECT NAME, CUSTOMERS_NUMBER AS customersNumber " +
            "FROM LAWYERS " +
            "ORDER BY CUSTOMERS_NUMBER DESC " +
            "LIMIT 1) " +
            "UNION ALL " +
            "(SELECT NAME, CUSTOMERS_NUMBER " +
            "FROM LAWYERS " +
            "ORDER BY CUSTOMERS_NUMBER ASC " +
            "LIMIT 1) " +
            "UNION ALL " +
            "(SELECT 'Average', TRUNC(AVG(CUSTOMERS_NUMBER), 0) " +
            "FROM LAWYERS)")
    List<LawyerMinProjection> search1();

}
