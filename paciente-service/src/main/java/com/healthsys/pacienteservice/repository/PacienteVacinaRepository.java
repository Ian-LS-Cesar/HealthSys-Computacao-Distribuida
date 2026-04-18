package com.healthsys.pacienteservice.repository;

import com.healthsys.pacienteservice.model.PacienteVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PacienteVacinaRepository extends JpaRepository<PacienteVacina, UUID> {
    List<PacienteVacina> findByPacienteId(UUID pacienteId);
}
