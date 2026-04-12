package com.healthsys.pacientes.repository;

import com.healthsys.pacientes.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {
    List<Alergia> findByPacienteId(UUID pacienteId);
}
