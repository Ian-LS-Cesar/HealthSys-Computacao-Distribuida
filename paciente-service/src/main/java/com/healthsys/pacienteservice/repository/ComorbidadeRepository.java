package com.healthsys.pacienteservice.repository;

import com.healthsys.pacienteservice.model.Comorbidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Integer> {
    boolean existsByDescricaoIgnoreCase(String descricao);
    Optional<Comorbidade> findByDescricaoIgnoreCase(String descricao);

    @Query("select c from Paciente p join p.comorbidades c where p.id = :pacienteId")
    List<Comorbidade> findByPacienteId(@Param("pacienteId") UUID pacienteId);
}
