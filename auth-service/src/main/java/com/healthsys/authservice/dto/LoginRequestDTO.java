package com.healthsys.authservice.dto;

import com.healthsys.authservice.dto.validators.CreateUsuarioValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(groups= CreateUsuarioValidationGroup.class, message = "Senha é obrigatória.")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    private String senha;
}
