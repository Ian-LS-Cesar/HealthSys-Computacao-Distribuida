package com.healthsys.triagemservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TriagemRequestDTO {
    @NotNull(message = "O campo paciente é obrigatório")
    private UUID paciente;

    @NotNull(message = "O campo risco é obrigatório")
    private Integer risco;

    @NotNull(message = "O campo de status é obrigatório")
    private Integer status;

    @NotNull(message = "O campo de data é obrigatório")
    private String dataCriacao;

    private String temperatura;

    private String glicemia;

    private String frequenciaCardiaca;

    private String saturacaoOxigenio;

    private String frequenciaRespiratoria;
}
