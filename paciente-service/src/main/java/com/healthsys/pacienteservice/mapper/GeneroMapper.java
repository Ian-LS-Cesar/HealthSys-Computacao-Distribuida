package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.GeneroRequestDTO;
import com.healthsys.pacienteservice.dto.GeneroResponseDTO;
import com.healthsys.pacienteservice.model.Genero;

public class GeneroMapper {
    public static GeneroResponseDTO toDTO(Genero genero) {
        GeneroResponseDTO generoResponseDTO = new GeneroResponseDTO();
        generoResponseDTO.setId(genero.getId());
        generoResponseDTO.setDescricao(genero.getDescricao());
        return generoResponseDTO;
    }

    public static Genero toModel(GeneroRequestDTO generoRequestDTO) {
        Genero genero = new Genero();
        genero.setDescricao(generoRequestDTO.getDescricao());
        return genero;
    }
}
