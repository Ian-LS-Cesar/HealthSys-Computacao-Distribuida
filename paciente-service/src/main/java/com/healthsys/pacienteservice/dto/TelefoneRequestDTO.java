package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TelefoneRequestDTO {
    @NotNull(message = "ID do Paciente é obrigatório")
    private UUID paciente;

    @NotBlank(message="Número de telefone é obrigatório")
    private String numero;
}
