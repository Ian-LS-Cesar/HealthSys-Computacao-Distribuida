package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PacienteVacinaRequestDTO {

    @NotNull(message = "ID do paciente é obrigatório")
    private UUID pacienteId;

    @NotNull(message = "ID da vacina é obrigatório")
    private UUID vacinaId;

    @NotBlank(message = "Data da aplicação é obrigatória")
    private String dataAplicacao; // formato esperado: yyyy-MM-dd
}