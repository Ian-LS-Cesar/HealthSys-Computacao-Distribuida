package com.healthsys.triagemservice.mapper;

import com.healthsys.triagemservice.dto.TriagemRequestDTO;
import com.healthsys.triagemservice.dto.TriagemResponseDTO;
import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.model.Status;
import com.healthsys.triagemservice.model.Triagem;

import java.time.LocalDateTime;

public class TriagemMapper {

    public static TriagemResponseDTO toDTO(Triagem triagem){
        TriagemResponseDTO triagemResponseDTO = new TriagemResponseDTO();
        triagemResponseDTO.setId(triagem.getId());
        triagemResponseDTO.setPaciente(triagem.getPaciente());
        triagemResponseDTO.setRisco(triagem.getRisco().getId());
        triagemResponseDTO.setStatus(triagem.getStatus().getId());
        triagemResponseDTO.setDataCriacao(triagem.getDataCriacao());
        return triagemResponseDTO;
    }

    public static Triagem toModel(TriagemRequestDTO triagemRequestDTO, Risco risco, Status status){
        Triagem triagem = new Triagem();
        triagem.setPaciente(triagemRequestDTO.getPaciente());
        triagem.setDataCriacao(LocalDateTime.now());
        triagem.setRisco(risco);
        triagem.setStatus(status);
        return triagem;
    }
}
