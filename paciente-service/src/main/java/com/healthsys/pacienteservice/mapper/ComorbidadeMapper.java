package com.healthsys.pacienteservice.mapper;


import com.healthsys.pacienteservice.dto.ComorbidadeRequestDTO;
import com.healthsys.pacienteservice.dto.ComorbidadeResponseDTO;
import com.healthsys.pacienteservice.model.Comorbidade;

public class ComorbidadeMapper {

    public static ComorbidadeResponseDTO toDTO (Comorbidade comorbidade){
        ComorbidadeResponseDTO comorbidadeDTO = new ComorbidadeResponseDTO();
        comorbidadeDTO.setId(comorbidade.getId());
        comorbidadeDTO.setDescricao(comorbidade.getDescricao());
        return comorbidadeDTO;
    }

    public static Comorbidade toModel(ComorbidadeRequestDTO comorbidadeDTO){
        Comorbidade comorbidade = new Comorbidade();
        comorbidade.setDescricao(comorbidadeDTO.getDescricao());
        return comorbidade;
    }
}
