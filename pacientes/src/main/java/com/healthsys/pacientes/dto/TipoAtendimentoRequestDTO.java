package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoAtendimentoRequestDTO {
    @NotBlank(message = "Descrição do tipo de atendimento é obrigatória")
    @Size(max = 255, message = "Descrição não pode exceder 255 caracteres")
    private String descricao;
}
