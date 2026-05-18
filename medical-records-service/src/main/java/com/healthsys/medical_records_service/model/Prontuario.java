package com.healthsys.medical_records_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "prontuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prontuario {

    @Id
    private String id;
    
    private String pacienteId; // ID de referência do paciente
    
    private Long leitoId; // ID do leito vinculado (Vem do Postgres do bed-service)
    
    private String historicoClinico; // Descrição da evolução clínica, sintomas ou evolução
    
    private String status; // Estados do fluxo: ATENDIMENTO, INTERNADO, ALTA
    
    private LocalDateTime dataRegistro = LocalDateTime.now();
}