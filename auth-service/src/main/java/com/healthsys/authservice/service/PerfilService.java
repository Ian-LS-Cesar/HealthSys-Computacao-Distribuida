package com.healthsys.authservice.service;

import com.healthsys.authservice.dto.PerfilRequestDTO;
import com.healthsys.authservice.dto.PerfilResponseDTO;
import com.healthsys.authservice.exception.PerfilAlreadyExistsException;
import com.healthsys.authservice.mapper.PerfilMapper;
import com.healthsys.authservice.model.Perfil;
import com.healthsys.authservice.repository.PerfilRepository;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Cacheable(value = "perfis")
    public List<PerfilResponseDTO> getPerfis(){
        List<Perfil> perfis = perfilRepository.findAll();
        return perfis.stream()
                .map(PerfilMapper::toDTO)
                .toList();
    }

    @CacheEvict(value = "perfis", allEntries = true)
    public PerfilResponseDTO criarPerfis(PerfilRequestDTO perfilRequestDTO){
        if (perfilRepository.existsByDescricaoIgnoreCase(perfilRequestDTO.getDescricao())){
            throw new PerfilAlreadyExistsException(
                    "Já existe um perfil com essa descrição: "+ perfilRequestDTO.getDescricao()
            );
        }

        Perfil novoPerfil = PerfilMapper.toModel(perfilRequestDTO);
        Perfil perfil = perfilRepository.save(novoPerfil);
        return PerfilMapper.toDTO(perfil);
    }

    @CacheEvict(value = "perfis", allEntries = true)
    public void deletarPerfil(int id){
        perfilRepository.deleteById(id);
    }
}
