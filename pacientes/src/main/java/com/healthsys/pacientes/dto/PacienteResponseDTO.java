package com.healthsys.pacientes.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PacienteResponseDTO {
    private String id;
    private String nome;
    private String nomeSocial;
    private String dataNascimento;
    private String genero;
    private String sexo;
    private String telefone;


}
