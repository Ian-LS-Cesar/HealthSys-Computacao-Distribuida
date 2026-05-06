package com.healthsys.pacienteservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name= "paciente_vacina")
@Getter
@Setter
@NoArgsConstructor
public class PacienteVacina {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paciente_id", referencedColumnName ="id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vacina_id", referencedColumnName = "id", nullable = false)
    private Vacina vacina;

    @Column(name="data_aplicacao")
    private LocalDate dataAplicacao;
}
