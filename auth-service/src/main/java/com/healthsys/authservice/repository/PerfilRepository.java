package com.healthsys.authservice.repository;

import com.healthsys.authservice.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    boolean existsByDescricaoIgnoreCase(String descricao);
}
