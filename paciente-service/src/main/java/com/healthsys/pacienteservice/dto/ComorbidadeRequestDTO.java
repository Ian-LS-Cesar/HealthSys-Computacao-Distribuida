package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComorbidadeRequestDTO {
    @NotBlank(message = "A descrição da comorbidade é obrigatória.")
    private String descricao;
}
