package com.healthsys.pacienteservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<String> telefones;
    private String cpf;
    private List<String> alergias;
    private List<EnderecoResponseDTO> enderecos;
    private List<String> comorbidades;
}
