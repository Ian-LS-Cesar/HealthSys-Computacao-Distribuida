package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.AlergiaRequestDTO;
import com.healthsys.pacienteservice.dto.AlergiaResponseDTO;
import com.healthsys.pacienteservice.model.Alergia;
import com.healthsys.pacienteservice.model.Paciente;

public class AlergiaMapper {
    public static AlergiaResponseDTO toDTO(Alergia alergia) {
        AlergiaResponseDTO alergiaResponseDTO = new AlergiaResponseDTO();
        alergiaResponseDTO.setId(alergia.getId());
        alergiaResponseDTO.setPaciente(alergia.getPaciente().getId());
        alergiaResponseDTO.setDescricao(alergia.getDescricao());
        return alergiaResponseDTO;
    }

    public static Alergia toModel(AlergiaRequestDTO alergiaRequestDTO, Paciente paciente) {
        Alergia alergia = new Alergia();
        alergia.setDescricao(alergiaRequestDTO.getDescricao());
        alergia.setPaciente(paciente);
        return alergia;
    }
}
