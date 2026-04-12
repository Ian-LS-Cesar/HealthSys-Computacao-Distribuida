package com.healthsys.pacientes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "atendimento")
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", referencedColumnName ="id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_atendimento", referencedColumnName ="id", nullable = false)
    private TipoAtendimento tipoAtendimento;

    @Column(nullable = false)
    private LocalDate dataAtendimento;

    @Column(nullable = false)
    private String observacao;



}
