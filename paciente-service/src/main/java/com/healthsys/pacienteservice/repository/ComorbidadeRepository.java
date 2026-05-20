package com.healthsys.pacienteservice.repository;

import com.healthsys.pacienteservice.model.Comorbidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Integer> {
    boolean existsByDescricaoIgnoreCase(String descricao);
    Optional<Comorbidade> findByDescricaoIgnoreCase(String descricao);
}
