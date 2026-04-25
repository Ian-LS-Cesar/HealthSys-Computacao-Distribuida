package com.healthsys.triagemservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RiscoRequestDTO {
    @NotBlank(message = "A descrição do risco é obrigatória.")
    private String descricao;
}
