package com.healthsys.pacientes.repository;

import com.healthsys.pacientes.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, UUID> {
    List<Vacina> findByPacienteId(UUID pacienteId);
}
