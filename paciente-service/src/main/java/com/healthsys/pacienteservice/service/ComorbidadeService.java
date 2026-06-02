package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.ComorbidadeRequestDTO;
import com.healthsys.pacienteservice.dto.ComorbidadeResponseDTO;
import com.healthsys.pacienteservice.exception.PacienteNotFoundException;
import com.healthsys.pacienteservice.mapper.ComorbidadeMapper;
import com.healthsys.pacienteservice.model.Comorbidade;
import com.healthsys.pacienteservice.repository.PacienteRepository;
import com.healthsys.pacienteservice.repository.ComorbidadeRepository;
import org.springframework.cache.annotation.CacheEvict; // <-- Adicionado
import org.springframework.cache.annotation.Cacheable; // <-- Adicionado
import org.springframework.cache.annotation.Caching;   // <-- Adicionado
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComorbidadeService {
    public final ComorbidadeRepository comorbidadeRepository;
    private final PacienteRepository pacienteRepository;

    public ComorbidadeService(ComorbidadeRepository comorbidadeRepository, PacienteRepository pacienteRepository) {
        this.comorbidadeRepository = comorbidadeRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // 1. Cache da listagem global de comorbidades do sistema
    @Cacheable(value = "comorbidades_todas", key = "'lista'")
    public List<ComorbidadeResponseDTO> getComorbidades(){
        List<Comorbidade> comorbidades = comorbidadeRepository.findAll();
        return comorbidades.stream()
                .map(ComorbidadeMapper::toDTO)
                .toList();
    }

    // 2. Cache dinâmico por pacienteId (isla o cache de cada paciente)
    @Cacheable(value = "comorbidades_paciente", key = "#pacienteId")
    public List<ComorbidadeResponseDTO> getComorbidadesPorPaciente(UUID pacienteId) {
        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + pacienteId));

        return comorbidadeRepository.findByPacienteId(pacienteId)
                .stream()
                .map(ComorbidadeMapper::toDTO)
                .toList();
    }

    // 3. Ao criar, limpamos a lista geral e o cache do paciente específico envolvido
    @Caching(evict = {
            @CacheEvict(value = "comorbidades_todas", key = "'lista'"),
            @CacheEvict(value = "comorbidades_paciente", key = "#comorbidadeRequestDTO.pacienteId", condition = "#comorbidadeRequestDTO.pacienteId != null")
    })
    public ComorbidadeResponseDTO criarComorbidade(ComorbidadeRequestDTO comorbidadeRequestDTO){
        if (comorbidadeRepository.existsByDescricaoIgnoreCase(comorbidadeRequestDTO.getDescricao())) {
            throw new IllegalArgumentException("Já existe uma comorbidade com essa descrição: " + comorbidadeRequestDTO.getDescricao()
            );
        }

        Comorbidade novaComorbidade = ComorbidadeMapper.toModel(comorbidadeRequestDTO);
        Comorbidade comorbidade = comorbidadeRepository.save(novaComorbidade);
        return ComorbidadeMapper.toDTO(comorbidade);
    }

    // 4. Ao atualizar, limpamos a lista geral e também o cache de pacientes (allEntries garante que nenhum fique desatualizado)
    @Caching(evict = {
            @CacheEvict(value = "comorbidades_todas", key = "'lista'"),
            @CacheEvict(value = "comorbidades_paciente", allEntries = true)
    })
    public ComorbidadeResponseDTO atualizarComorbidade(Integer id, ComorbidadeRequestDTO comorbidadeRequestDTO) {
        Comorbidade comorbidade = comorbidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comorbidade não encontrada com ID: " + id));

        String descricaoTrimmed = comorbidadeRequestDTO.getDescricao().trim();
        Comorbidade comorbidadeExistente = comorbidadeRepository.findByDescricaoIgnoreCase(descricaoTrimmed).orElse(null);

        if (comorbidadeExistente != null && comorbidadeExistente.getId() != id) {
            throw new IllegalArgumentException("Já existe uma comorbidade com essa descrição: " + descricaoTrimmed);
        }

        comorbidade.setDescricao(descricaoTrimmed);
        return ComorbidadeMapper.toDTO(comorbidadeRepository.save(comorbidade));
    }

    // 5. Ao deletar, limpamos todas as frentes de cache para evitar leituras de dados inexistentes
    @Caching(evict = {
            @CacheEvict(value = "comorbidades_todas", key = "'lista'"),
            @CacheEvict(value = "comorbidades_paciente", allEntries = true)
    })
    public void deletarComorbidade(int id){
        comorbidadeRepository.deleteById(id);
    }
}