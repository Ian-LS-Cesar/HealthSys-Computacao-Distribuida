package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.SexoRequestDTO;
import com.healthsys.pacientes.dto.SexoResponseDTO;
import com.healthsys.pacientes.model.Sexo;

public class SexoMapper {
    public static SexoResponseDTO toDTO(Sexo sexo) {
        SexoResponseDTO sexoResponseDTO = new SexoResponseDTO();
        sexoResponseDTO.setId(sexo.getId());
        sexoResponseDTO.setDescricao(sexo.getDescricao());
        return sexoResponseDTO;
    }

    public static Sexo toModel(SexoRequestDTO sexoRequestDTO) {
        Sexo sexo = new Sexo();
        sexo.setDescricao(sexoRequestDTO.getDescricao());
        return sexo;
    }
}
