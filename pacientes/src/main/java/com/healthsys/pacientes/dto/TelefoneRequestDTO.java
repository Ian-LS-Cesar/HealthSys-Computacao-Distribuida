package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneRequestDTO {
    @NotBlank(message="Número de telefone é obrigatório")
    private String numero;
}
