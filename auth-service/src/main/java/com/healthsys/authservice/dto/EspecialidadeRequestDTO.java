package com.healthsys.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadeRequestDTO {
    @NotBlank(message="Descrição não pode estar em branco")
    private String descricao;
}
