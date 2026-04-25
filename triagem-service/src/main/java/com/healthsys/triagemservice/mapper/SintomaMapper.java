package com.healthsys.triagemservice.mapper;


import com.healthsys.triagemservice.dto.SintomaRequestDTO;
import com.healthsys.triagemservice.dto.SintomaResponseDTO;
import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.model.Sintoma;

public class SintomaMapper {
    public static SintomaResponseDTO toDTO (Sintoma sintoma) {
        SintomaResponseDTO sintomaResponseDTO = new SintomaResponseDTO();
        sintomaResponseDTO.setId(sintoma.getId());
        sintomaResponseDTO.setDescricao(sintoma.getDescricao());
        sintomaResponseDTO.setRisco(sintoma.getRisco().getId());
        return sintomaResponseDTO;
    }

    public static Sintoma toModel (SintomaRequestDTO sintomaResponseDTO, Risco risco) {
        Sintoma sintoma = new Sintoma();
        sintoma.setDescricao(sintomaResponseDTO.getDescricao());
        sintoma.setRisco(risco);
        return sintoma;
    }
}
