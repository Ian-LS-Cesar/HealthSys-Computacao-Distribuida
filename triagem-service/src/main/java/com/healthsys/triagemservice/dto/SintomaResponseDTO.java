package com.healthsys.triagemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SintomaResponseDTO {
    private Integer id;
    private String descricao;
    private Integer risco;
}
