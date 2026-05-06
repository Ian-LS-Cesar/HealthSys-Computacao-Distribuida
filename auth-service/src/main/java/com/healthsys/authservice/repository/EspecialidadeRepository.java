package com.healthsys.authservice.repository;

import com.healthsys.authservice.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
    boolean existsByDescricaoIgnoreCase(String descricao);
}
