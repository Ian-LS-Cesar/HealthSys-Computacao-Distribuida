package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.SexoRequestDTO;
import com.healthsys.pacienteservice.dto.SexoResponseDTO;
import com.healthsys.pacienteservice.mapper.SexoMapper;
import com.healthsys.pacienteservice.model.Sexo;
import com.healthsys.pacienteservice.repository.SexoRepository;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict; // <-- Adicionado
import org.springframework.cache.annotation.Cacheable; // <-- Adicionado
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class SexoService {
    private final SexoRepository sexoRepository;

    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    // Busca a lista diretamente do Redis se ela já tiver sido consultada uma vez
    @Cacheable(value = "sexos_todos", key = "'lista'")
    public List<SexoResponseDTO> getSexos() {
        List<Sexo> sexos = sexoRepository.findAll();
        return sexos.stream()
                .map(SexoMapper::toDTO)
                .toList();
    }

    // Invalida a lista antiga cacheada quando um novo registro for inserido
    @CacheEvict(value = "sexos_todos", key = "'lista'")
    public SexoResponseDTO criarSexo(SexoRequestDTO dto) {
        Sexo novoSexo = SexoMapper.toModel(dto);
        return SexoMapper.toDTO(sexoRepository.save(novoSexo));
    }

    // Invalida o cache para que a alteração da descrição se reflita imediatamente nas consultas
    @CacheEvict(value = "sexos_todos", key = "'lista'")
    public SexoResponseDTO atualizarSexo(Integer id, SexoRequestDTO dto) {
        Sexo sexo = sexoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sexo não encontrado com ID: " + id));

        sexo.setDescricao(dto.getDescricao());
        return SexoMapper.toDTO(sexoRepository.save(sexo));
    }

    // Remove a lista obsoleta do cache após a exclusão do registro no banco
    @CacheEvict(value = "sexos_todos", key = "'lista'")
    public void deletarSexo(Integer id) {
        sexoRepository.deleteById(id);
    }
}