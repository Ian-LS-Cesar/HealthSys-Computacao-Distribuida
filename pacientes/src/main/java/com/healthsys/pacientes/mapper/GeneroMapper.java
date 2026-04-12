package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.GeneroRequestDTO;
import com.healthsys.pacientes.dto.GeneroResponseDTO;
import com.healthsys.pacientes.model.Genero;

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
