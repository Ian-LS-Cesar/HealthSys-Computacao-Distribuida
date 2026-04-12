package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SexoRequestDTO {
    @NotBlank(message = "A descrição do sexo é obrigatória.")
    private String descricao;
}
