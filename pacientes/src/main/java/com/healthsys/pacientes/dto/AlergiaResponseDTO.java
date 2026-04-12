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
public class AlergiaResponseDTO {
    private int id;
    private UUID paciente;
    private String descricao;
}
