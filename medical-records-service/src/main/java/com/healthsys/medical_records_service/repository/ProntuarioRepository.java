package com.healthsys.medical_records_service.repository;

import com.healthsys.medical_records_service.model.Prontuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends MongoRepository<Prontuario, String> {
    // Métodos herdados do MongoRepository para operações CRUD nativas no MongoDB
}