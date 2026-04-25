package com.healthsys.triagemservice.repository;

import com.healthsys.triagemservice.model.Risco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiscoRepository extends JpaRepository<Risco, Integer> {
}
