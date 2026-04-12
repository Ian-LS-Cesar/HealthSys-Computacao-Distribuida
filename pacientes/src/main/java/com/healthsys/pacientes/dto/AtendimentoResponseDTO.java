package com.healthsys.pacientes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoResponseDTO {
    private UUID id;
    private UUID paciente;
    private int tipoAtendimento;
    private String observacao;
    private String dataAtendimento;
}
