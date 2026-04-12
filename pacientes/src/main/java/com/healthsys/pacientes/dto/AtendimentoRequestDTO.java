package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoRequestDTO {
    @NotNull(message = "ID do Paciente é obrigatório")
    private UUID paciente;

    @NotNull(message = "ID do Tipo de Atendimento é obrigatório")
    private int tipoAtendimento;

    @NotBlank(message = "Observação é obrigatória")
    private String observacao;

    @NotBlank(message = "Data do Atendimento é obrigatória")
    private String dataAtendimento;
}
