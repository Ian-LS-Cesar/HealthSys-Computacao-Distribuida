package com.healthsys.pacienteservice.repository;

import com.healthsys.pacienteservice.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, UUID> {
}
