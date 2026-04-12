package com.healthsys.authservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @NotNull
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id", columnDefinition = "int default 0")
    private Perfil perfil;
}
