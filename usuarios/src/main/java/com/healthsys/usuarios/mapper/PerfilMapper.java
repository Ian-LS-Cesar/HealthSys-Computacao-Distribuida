package com.healthsys.usuarios.mapper;

import com.healthsys.usuarios.dto.PerfilResponseDTO;
import com.healthsys.usuarios.model.Perfil;

public class PerfilMapper {
    public static PerfilResponseDTO toDTO(Perfil perfil){
        PerfilResponseDTO perfilDTO = new PerfilResponseDTO();
        perfilDTO.setId(perfil.getId());
        perfilDTO.setDescricao(perfil.getDescricao());
        return perfilDTO;
    }
}
