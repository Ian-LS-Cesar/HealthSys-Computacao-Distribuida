package com.healthsys.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotBlank(message = "O nome do perfil é obrigatório.")
    private String nome;

    @NotBlank
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(message = "Data de nascimento é obrigatória.")
    private String dataNascimento;

    @NotBlank(message = "Senha é obrigatória.")
    private String senha;

    @NotNull(message = "O ID do perfil é obrigatório.")
    private Integer perfil;

}
