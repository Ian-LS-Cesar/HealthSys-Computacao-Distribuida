package com.healthsys.pacientes.repository;

import com.healthsys.pacientes.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
    List<Atendimento> findByPacienteId(UUID pacienteId);
}
