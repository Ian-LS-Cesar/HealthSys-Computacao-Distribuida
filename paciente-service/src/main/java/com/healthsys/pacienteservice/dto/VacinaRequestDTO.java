package com.healthsys.pacienteservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VacinaRequestDTO {
    @NotBlank(message= "Nome da vacina é obrigatório")
    private String nome;
}
