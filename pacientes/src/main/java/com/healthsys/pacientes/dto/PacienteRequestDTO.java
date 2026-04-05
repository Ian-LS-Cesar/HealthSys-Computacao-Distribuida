package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteRequestDTO {
    @NotBlank(message="Nome não pode estar em branco")
    @Size(max=100, message= "Nome não pode exceder 100 caracteres")
    private String nome;

    private String nomeSocial;

    @NotBlank(message="Data de Nascimento é obrigatória")
    private String dataNascimento;

    @NotNull(message="Gênero é obrigatório")
    private Integer genero;

    @NotNull(message="Sexo é obrigatório")
    private Integer sexo;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

}
