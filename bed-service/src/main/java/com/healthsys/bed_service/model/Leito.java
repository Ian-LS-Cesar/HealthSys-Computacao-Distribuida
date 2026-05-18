package com.healthsys.bed_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "leitos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo; // Ex: UTI-01, ENF-A-102

    @Column(nullable = false)
    private String ala; // Ex: UTI, ENFERMARIA, PEDIATRIA

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLeito status = StatusLeito.LIVRE;

    // Armazena o ID do paciente vindo do prontuário/cadastro se estiver ocupado
    private String pacienteId; 
}