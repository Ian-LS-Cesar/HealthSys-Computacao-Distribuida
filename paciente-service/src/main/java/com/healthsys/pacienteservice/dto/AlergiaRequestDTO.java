package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlergiaRequestDTO {

    @NotBlank(message = "Descrição da alergia é obrigatória")
    @Size(max = 120, message = "Descrição não pode exceder 120 caracteres")
    private String descricao;
}
