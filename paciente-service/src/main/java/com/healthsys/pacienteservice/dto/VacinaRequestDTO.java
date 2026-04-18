package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VacinaRequestDTO {
    @NotBlank(message= "Nome da vacina é obrigatório")
    private String nome;
}
