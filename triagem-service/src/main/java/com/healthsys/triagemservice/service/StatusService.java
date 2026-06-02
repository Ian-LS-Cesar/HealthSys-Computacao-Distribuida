package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.dto.StatusRequestDTO;
import com.healthsys.triagemservice.dto.StatusResponseDTO;
import com.healthsys.triagemservice.mapper.StatusMapper;
import com.healthsys.triagemservice.model.Status;
import com.healthsys.triagemservice.repository.StatusRepository;
import lombok.Getter;
import org.springframework.cache.annotation.CacheEvict; // <-- Adicionado
import org.springframework.cache.annotation.Cacheable; // <-- Adicionado
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    // Intercepta a chamada HTTP e busca a lista direto do contêiner Redis se ela existir
    @Cacheable(value = "status_todos", key = "'lista'")
    public List<StatusResponseDTO> getStatus(){
        List<Status> status = statusRepository.findAll();
        return status.stream()
                .map(StatusMapper::toDTO)
                .toList();
    }

    // Remove a lista obsoleta do cache ao salvar um novo status
    @CacheEvict(value = "status_todos", key = "'lista'")
    public StatusResponseDTO criarStatus(StatusRequestDTO statusRequestDTO){
        Status novoStatus = StatusMapper.toModel(statusRequestDTO);
        return StatusMapper.toDTO(statusRepository.save(novoStatus));
    }

    // Remove a lista do cache para forçar a atualização dos dados editados na próxima consulta
    @CacheEvict(value = "status_todos", key = "'lista'")
    public StatusResponseDTO atualizarStatus(Integer id, StatusRequestDTO statusRequestDTO){
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Status não encontrado com ID: " + id));

        status.setDescricao(statusRequestDTO.getDescricao());
        return StatusMapper.toDTO(statusRepository.save(status));
    }

    // Limpa o cache após a remoção física do registro no banco relacional
    @CacheEvict(value = "status_todos", key = "'lista'")
    public void deletarStatus(Integer id){
        statusRepository.deleteById(id);
    }
}