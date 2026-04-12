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
public class TelefoneResponseDTO {
    private int id;
    private UUID paciente;
    private String numero;
}
