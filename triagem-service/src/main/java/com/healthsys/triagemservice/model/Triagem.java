package com.healthsys.triagemservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risco", referencedColumnName = "id", nullable = false)
    private Risco risco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;
}
