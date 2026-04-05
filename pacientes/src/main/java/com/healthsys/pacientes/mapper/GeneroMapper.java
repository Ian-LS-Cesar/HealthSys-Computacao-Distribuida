package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.GeneroDTO;
import com.healthsys.pacientes.model.Genero;

public class GeneroMapper {
    public static GeneroDTO toDTO(Genero genero) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(genero.getId());
        generoDTO.setDescricao(genero.getDescricao());
        return generoDTO;
    }
}
