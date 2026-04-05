package com.healthsys.pacientes.dto;


import lombok.Getter;
import lombok.Setter;

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
