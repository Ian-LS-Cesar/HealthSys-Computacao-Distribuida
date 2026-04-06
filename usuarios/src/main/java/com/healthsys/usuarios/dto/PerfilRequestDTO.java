package com.healthsys.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilRequestDTO {
    @NotBlank(message="Descrição não pode estar em branco")
    private String descricao;

}
