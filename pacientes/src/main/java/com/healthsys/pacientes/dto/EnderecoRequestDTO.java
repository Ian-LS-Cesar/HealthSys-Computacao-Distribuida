package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDTO {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;

    @Size(min = 2, max= 2, message="UF deve ter 2 caracteres")
    private String uf;
    private String cep;
}
