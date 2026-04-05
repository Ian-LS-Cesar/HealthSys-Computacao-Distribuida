package com.healthsys.pacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String numero;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paciente_id", referencedColumnName = "id")
    private Paciente paciente;
}
