package com.healthsys.pacientes.dto;

import com.healthsys.pacientes.model.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteRequestDTO {
    @NotBlank
    @Size(max=100, message= "Nome não pode exceder 100 caracteres")
    private String nome;

    private String nomeSocial;

    @NotBlank(message="Data de Nascimento é obrigatória")
    private String dataNascimento;

    @NotNull(message="Gênero é obrigatório")
    private Integer generoId;

    @NotNull(message="Sexo é obrigatório")
    private Integer sexoId;

    @NotNull(message = "Telefone é obrigatório")
    private Telefone telefone;

}
