package com.healthsys.pacienteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteVacinaResponseDTO {
    private UUID id;
    private UUID pacienteId;
    private UUID vacinaId;
    private String nomeVacina;
    private String dataAplicacao;
}