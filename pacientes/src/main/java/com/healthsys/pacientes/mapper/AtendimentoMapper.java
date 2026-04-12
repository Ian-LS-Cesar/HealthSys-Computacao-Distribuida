package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.AtendimentoRequestDTO;
import com.healthsys.pacientes.dto.AtendimentoResponseDTO;
import com.healthsys.pacientes.model.Atendimento;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.model.TipoAtendimento;

import java.time.LocalDate;

public class AtendimentoMapper {

    // ✅ Inclui paciente e tipo de atendimento na resposta
    public static AtendimentoResponseDTO toDTO(Atendimento atendimento) {
        AtendimentoResponseDTO atendimentoResponseDTO = new AtendimentoResponseDTO();
        atendimentoResponseDTO.setId(atendimento.getId());
        atendimentoResponseDTO.setPaciente(atendimento.getPaciente().getId());
        atendimentoResponseDTO.setTipoAtendimento(atendimento.getTipoAtendimento().getId());
        atendimentoResponseDTO.setObservacao(atendimento.getObservacao());
        atendimentoResponseDTO.setDataAtendimento(atendimento.getDataAtendimento().toString());
        return atendimentoResponseDTO;
    }

    public static Atendimento toModel(
            AtendimentoRequestDTO atendimentoRequestDTO,
            Paciente paciente,
            TipoAtendimento tipoAtendimento) {
        Atendimento atendimento = new Atendimento();
        atendimento.setPaciente(paciente);
        atendimento.setTipoAtendimento(tipoAtendimento);
        atendimento.setObservacao(atendimentoRequestDTO.getObservacao());
        atendimento.setDataAtendimento(LocalDate.parse(atendimentoRequestDTO.getDataAtendimento()));
        return atendimento;
    }
}
