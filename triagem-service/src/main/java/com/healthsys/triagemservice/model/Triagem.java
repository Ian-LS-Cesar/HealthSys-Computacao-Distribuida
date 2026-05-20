package com.healthsys.triagemservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Triagem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "paciente", nullable = false)
    private UUID paciente;

    @Column(name = "temperatura")
    private String temperatura;

    @Column(name = "glicemia")
    private String glicemia;

    @Column(name="frequencia_cardiaca")
    private String frequenciaCardiaca;

    @Column(name="saturacao_oxigenio")
    private String saturacaoOxigenio;

    @Column(name="frequencia_respiratoria")
    private String frequenciaRespiratoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risco", referencedColumnName = "id", nullable = false)
    private Risco risco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
}
