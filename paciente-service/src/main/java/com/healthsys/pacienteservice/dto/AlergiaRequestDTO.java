package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AlergiaRequestDTO {

    @NotNull(message = "ID do Paciente é obrigatório")
    private UUID paciente;

    @NotBlank(message = "Descrição da alergia é obrigatória")
    @Size(max = 120, message = "Descrição não pode exceder 120 caracteres")
    private String descricao;
}
