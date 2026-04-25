package com.healthsys.triagemservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequestDTO {
    @NotBlank(message = "A descrição do status é obrigatória.")
    private String descricao;
}
