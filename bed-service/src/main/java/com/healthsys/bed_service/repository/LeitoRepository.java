package com.healthsys.bed_service.repository;

import com.healthsys.bed_service.model.Leito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {
    List<Leito> findByAlaAndStatus(String ala, com.healthsys.bed_service.model.StatusLeito status);
}