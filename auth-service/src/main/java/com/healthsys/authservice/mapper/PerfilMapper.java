package com.healthsys.authservice.mapper;

import com.healthsys.authservice.dto.PerfilRequestDTO;
import com.healthsys.authservice.dto.PerfilResponseDTO;
import com.healthsys.authservice.model.Perfil;

public class PerfilMapper {
    public static PerfilResponseDTO toDTO(Perfil perfil){
        PerfilResponseDTO perfilDTO = new PerfilResponseDTO();
        perfilDTO.setId(perfil.getId());
        perfilDTO.setDescricao(perfil.getDescricao());
        return perfilDTO;
    }

    public static Perfil toModel(PerfilRequestDTO perfilRequestDTO){
        Perfil perfil = new Perfil();
        perfil.setDescricao(perfilRequestDTO.getDescricao());
        return perfil;
    }
}
