package com.healthsys.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private String id;
    private String nome;
    private String dataNascimento;
    private String email;
    private String senha;
    private String perfil;
    private String especialidade;
}
