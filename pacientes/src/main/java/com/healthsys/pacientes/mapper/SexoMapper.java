package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.SexoDTO;
import com.healthsys.pacientes.model.Sexo;

public class SexoMapper {
    public static SexoDTO toDTO(Sexo sexo) {
        SexoDTO sexoDTO = new SexoDTO();
        sexoDTO.setId(sexo.getId());
        sexoDTO.setDescricao(sexo.getDescricao());
        return sexoDTO;
    }
}
