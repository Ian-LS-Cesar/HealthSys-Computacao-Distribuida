package com.healthsys.pacientes.repository;

import com.healthsys.pacientes.model.TipoAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento, Integer> {
}
