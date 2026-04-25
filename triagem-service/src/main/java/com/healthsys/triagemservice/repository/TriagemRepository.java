package com.healthsys.triagemservice.repository;

import com.healthsys.triagemservice.model.Triagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TriagemRepository extends JpaRepository<Triagem, UUID> {
    List<Triagem> findByPaciente(UUID idPaciente);
}
