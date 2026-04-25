package com.healthsys.triagemservice.repository;

import com.healthsys.triagemservice.model.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SintomaRepository extends JpaRepository<Sintoma, Integer> {
}
