package com.healthsys.authservice.service;

import com.healthsys.authservice.dto.PerfilRequestDTO;
import com.healthsys.authservice.dto.PerfilResponseDTO;
import com.healthsys.authservice.mapper.PerfilMapper;
import com.healthsys.authservice.model.Perfil;
import com.healthsys.authservice.repository.PerfilRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<PerfilResponseDTO> getPerfis(){
        List<Perfil> perfis = perfilRepository.findAll();
        return perfis.stream()
                .map(PerfilMapper::toDTO)
                .toList();
    }

    public PerfilResponseDTO criarPerfis(PerfilRequestDTO perfilRequestDTO){
        Perfil novoPerfil = PerfilMapper.toModel(perfilRequestDTO);
        Perfil perfil = perfilRepository.save(novoPerfil);
        return PerfilMapper.toDTO(perfil);
    }
}
