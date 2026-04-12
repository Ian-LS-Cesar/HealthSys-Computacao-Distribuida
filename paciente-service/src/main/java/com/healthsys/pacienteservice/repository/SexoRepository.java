package com.healthsys.pacienteservice.repository;

import com.healthsys.pacienteservice.model.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, Integer> {
}
