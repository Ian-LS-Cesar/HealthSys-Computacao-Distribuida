package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VacinaRequestDTO {
    @NotNull(message= "ID do Paciente é obrigatório")
    private UUID paciente;

    @NotBlank(message= "Nome da vacina é obrigatório")
    private String nome;

    @NotBlank(message = "Data da aplicação é obrigatória")
    private String dataAplicacao;
}
