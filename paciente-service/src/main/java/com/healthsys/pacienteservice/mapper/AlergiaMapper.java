package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.AlergiaRequestDTO;
import com.healthsys.pacienteservice.dto.AlergiaResponseDTO;
import com.healthsys.pacienteservice.model.Alergia;

public class AlergiaMapper {
    public static AlergiaResponseDTO toDTO(Alergia alergia) {
        AlergiaResponseDTO alergiaResponseDTO = new AlergiaResponseDTO();
        alergiaResponseDTO.setId(alergia.getId());
        alergiaResponseDTO.setDescricao(alergia.getDescricao());
        return alergiaResponseDTO;
    }

    public static Alergia toModel(AlergiaRequestDTO alergiaRequestDTO) {
        Alergia alergia = new Alergia();
        alergia.setDescricao(alergiaRequestDTO.getDescricao());
        return alergia;
    }
}
