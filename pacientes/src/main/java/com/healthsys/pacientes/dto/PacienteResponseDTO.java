package com.healthsys.pacientes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {
    private String id;
    private String nome;
    private String nomeSocial;
    private String dataNascimento;
    private String genero;
    private String sexo;
    private String telefone;
    private String cpf;

}
