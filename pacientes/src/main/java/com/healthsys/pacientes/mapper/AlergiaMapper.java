package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.AlergiaRequestDTO;
import com.healthsys.pacientes.dto.AlergiaResponseDTO;
import com.healthsys.pacientes.model.Alergia;
import com.healthsys.pacientes.model.Paciente;

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
