package com.healthsys.pacienteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacinaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String nome;
}
