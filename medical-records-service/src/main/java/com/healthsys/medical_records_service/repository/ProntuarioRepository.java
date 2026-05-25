package com.healthsys.medical_records_service.repository;

import com.healthsys.medical_records_service.model.Prontuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProntuarioRepository extends MongoRepository<Prontuario, String> {
    List<Prontuario> findByStatus(String status);
}