package com.healthsys.authservice.dto;

import com.healthsys.authservice.dto.validators.CreateUsuarioValidationGroup;
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

    @NotBlank(groups= CreateUsuarioValidationGroup.class, message = "Data de nascimento é obrigatória.")
    private String dataNascimento;

    @NotBlank(groups= CreateUsuarioValidationGroup.class, message = "Senha é obrigatória.")
    private String senha;

    @NotNull(groups= CreateUsuarioValidationGroup.class, message = "O ID do perfil é obrigatório.")
    private Integer perfil;

}
