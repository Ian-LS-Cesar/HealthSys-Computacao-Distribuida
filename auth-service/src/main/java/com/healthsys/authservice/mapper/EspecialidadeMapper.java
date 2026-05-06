package com.healthsys.authservice.mapper;

import com.healthsys.authservice.dto.EspecialidadeRequestDTO;
import com.healthsys.authservice.dto.EspecialidadeResponseDTO;
import com.healthsys.authservice.model.Especialidade;

public class EspecialidadeMapper {
    public static EspecialidadeResponseDTO toDTO(Especialidade especialidade){
        EspecialidadeResponseDTO dto = new EspecialidadeResponseDTO();
        especialidade.setId(especialidade.getId());
        dto.setDescricao(especialidade.getDescricao());
        return dto;
    }

    public static Especialidade toModel(EspecialidadeRequestDTO especialidadeRequestDTO){
        Especialidade dto = new Especialidade();
        dto.setDescricao(especialidadeRequestDTO.getDescricao());
        return dto;
    }
}
