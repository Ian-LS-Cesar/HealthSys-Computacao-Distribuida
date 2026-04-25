package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.dto.StatusRequestDTO;
import com.healthsys.triagemservice.dto.StatusResponseDTO;
import com.healthsys.triagemservice.mapper.StatusMapper;
import com.healthsys.triagemservice.model.Status;
import com.healthsys.triagemservice.repository.StatusRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<StatusResponseDTO> getStatus(){
        List<Status> status = statusRepository.findAll();
        return status.stream()
                .map(StatusMapper::toDTO)
                .toList();
    }

    public StatusResponseDTO criarStatus(StatusRequestDTO statusRequestDTO){
        Status novoStatus = StatusMapper.toModel(statusRequestDTO);
        return StatusMapper.toDTO(statusRepository.save(novoStatus));
    }

    public StatusResponseDTO atualizarStatus(Integer id, StatusRequestDTO statusRequestDTO){
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Status não encontrado com ID: " + id));

        status.setDescricao(statusRequestDTO.getDescricao());
        return StatusMapper.toDTO(statusRepository.save(status));
    }

    public void deletarStatus(Integer id){
        statusRepository.deleteById(id);
    }
}
