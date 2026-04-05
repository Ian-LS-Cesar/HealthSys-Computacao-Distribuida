package com.healthsys.pacientes.model;

import jakarta.persistence.*;

@Entity
@Table(name="sexo")
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

}
