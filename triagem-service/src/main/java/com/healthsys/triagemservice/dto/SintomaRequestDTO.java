package com.healthsys.triagemservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SintomaRequestDTO {
    @NotBlank(message = "Descrição do sintoma é obrigatória")
    private String descricao;

    @NotNull(message = "ID do risco associado é obrigatório")
    private int risco;

}
