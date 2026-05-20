package com.healthsys.pacienteservice.repository;

import com.healthsys.pacienteservice.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {
    Optional<Alergia> findByDescricaoIgnoreCase(String descricao);

    @Query("select a from Paciente p join p.alergias a where p.id = :pacienteId")
    List<Alergia> findByPacienteId(@Param("pacienteId") UUID pacienteId);
}
