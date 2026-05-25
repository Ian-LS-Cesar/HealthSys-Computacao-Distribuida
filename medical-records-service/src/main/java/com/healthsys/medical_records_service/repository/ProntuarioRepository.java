package com.healthsys.medical_records_service.repository;

import com.healthsys.medical_records_service.model.Prontuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProntuarioRepository extends MongoRepository<Prontuario, String> {
    
    /**
     * Busca prontuários filtrando pelo status (ex: "INTERNADO", "ALTA").
     * O Spring Data Mongo gera a query automaticamente com base no nome do método.
     */
    List<Prontuario> findByStatus(String status);
}