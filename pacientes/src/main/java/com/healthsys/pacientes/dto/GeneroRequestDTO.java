package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneroRequestDTO {
    @NotBlank(message = "A descrição do gênero é obrigatória.")
    private String descricao;
}
