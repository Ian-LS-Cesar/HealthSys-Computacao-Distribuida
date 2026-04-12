package com.healthsys.pacientes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EnderecoRequestDTO {
    @NotNull(message = "ID do Paciente é obrigatório")
    private UUID paciente;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;

    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
    private String uf;

    private String cep;
}
