package com.healthsys.triagemservice.mapper;

import com.healthsys.triagemservice.dto.RiscoResponseDTO;
import com.healthsys.triagemservice.dto.RiscoRequestDTO;
import com.healthsys.triagemservice.model.Risco;

public class RiscoMapper {
    public static RiscoResponseDTO toDTO (Risco risco){
        RiscoResponseDTO riscoDTO = new RiscoResponseDTO();
        riscoDTO.setId(risco.getId());
        riscoDTO.setDescricao(risco.getDescricao());
        return riscoDTO;
    }

    public static Risco toModel(RiscoRequestDTO riscoDTO){
        Risco risco = new Risco();
        risco.setDescricao(riscoDTO.getDescricao());
        return risco;
    }
}
