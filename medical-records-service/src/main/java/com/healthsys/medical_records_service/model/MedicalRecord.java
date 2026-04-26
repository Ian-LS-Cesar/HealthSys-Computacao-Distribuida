package com.healthsys.medical_records_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "records")
public class MedicalRecord {
    @Id
    private String id;
    private String patientName;
    private String diagnosis;
    private List<String> medications;
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters e Setters (ou use @Data se tiver o Lombok)
}