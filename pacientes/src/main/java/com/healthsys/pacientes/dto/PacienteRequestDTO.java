package com.healthsys.pacientes.dto;

import com.healthsys.pacientes.dto.validators.CreatePacienteValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PacienteRequestDTO {
    @NotBlank(message="Nome não pode estar em branco")
    @Size(max=100, message= "Nome não pode exceder 100 caracteres")
    private String nome;

    private String nomeSocial;

    @NotBlank(groups = CreatePacienteValidationGroup.class,  message ="Data de Nascimento é obrigatória")
    private String dataNascimento;

    @NotNull(groups = CreatePacienteValidationGroup.class, message ="Gênero é obrigatório")
    private Integer genero;

    @NotNull(groups = CreatePacienteValidationGroup.class, message="Sexo é obrigatório")
    private Integer sexo;

    @NotEmpty(groups = CreatePacienteValidationGroup.class, message = "Ao menos um telefone é obrigatório")
    private List<String> telefones;

    @NotBlank(groups = CreatePacienteValidationGroup.class, message = "CPF é obrigatório")
    private String cpf;

    private List<String> alergias;

    private List<EnderecoRequestDTO> enderecos;

}
