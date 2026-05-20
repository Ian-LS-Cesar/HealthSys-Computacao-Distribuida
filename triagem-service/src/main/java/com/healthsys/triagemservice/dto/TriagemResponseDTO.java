package com.healthsys.triagemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TriagemResponseDTO {
    private UUID id;
    private UUID paciente;
    private String temperatura;

    private String glicemia;

    private String frequenciaCardiaca;

    private String saturacaoOxigenio;

    private String frequenciaRespiratoria;
    private Integer risco;
    private Integer status;
    private LocalDateTime dataCriacao;
}
