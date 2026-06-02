package com.healthsys.triagemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SintomaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String descricao;
    private Integer risco;
}
