package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.TipoAtendimentoRequestDTO;
import com.healthsys.pacientes.dto.TipoAtendimentoResponseDTO;
import com.healthsys.pacientes.model.TipoAtendimento;

public class TipoAtendimentoMapper {
    public static TipoAtendimentoResponseDTO toDTO(TipoAtendimento tipoAtendimento) {
        TipoAtendimentoResponseDTO tipoAtendimentoResponseDTO = new TipoAtendimentoResponseDTO();
        tipoAtendimentoResponseDTO.setId(tipoAtendimento.getId());
        tipoAtendimentoResponseDTO.setDescricao(tipoAtendimento.getDescricao());
        return tipoAtendimentoResponseDTO;
    }

    public static TipoAtendimento toModel(TipoAtendimentoRequestDTO tipoAtendimentoRequestDTO) {
        TipoAtendimento tipoAtendimento = new TipoAtendimento();
        tipoAtendimento.setDescricao(tipoAtendimentoRequestDTO.getDescricao());
        return tipoAtendimento;
    }
}
