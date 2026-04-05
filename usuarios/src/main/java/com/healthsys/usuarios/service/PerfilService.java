package com.healthsys.usuarios.service;

import com.healthsys.usuarios.dto.PerfilResponseDTO;
import com.healthsys.usuarios.mapper.PerfilMapper;
import com.healthsys.usuarios.model.Perfil;
import com.healthsys.usuarios.repository.PerfilRepository;
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
}
