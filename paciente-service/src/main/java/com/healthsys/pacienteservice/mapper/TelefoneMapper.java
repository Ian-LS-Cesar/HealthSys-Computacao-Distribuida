package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.TelefoneRequestDTO;
import com.healthsys.pacienteservice.dto.TelefoneResponseDTO;
import com.healthsys.pacienteservice.model.Paciente;
import com.healthsys.pacienteservice.model.Telefone;

public class TelefoneMapper {
    public static TelefoneResponseDTO toDTO(Telefone telefone) {
        TelefoneResponseDTO telefoneResponseDTO = new TelefoneResponseDTO();
        telefoneResponseDTO.setId(telefone.getId());
        telefoneResponseDTO.setPaciente(telefone.getPaciente().getId());
        telefoneResponseDTO.setNumero(telefone.getNumero());
        return telefoneResponseDTO;
    }

    public static Telefone toModel(TelefoneRequestDTO telefoneRequestDTO, Paciente paciente) {
        Telefone telefone = new Telefone();
        telefone.setNumero(telefoneRequestDTO.getNumero());
        telefone.setPaciente(paciente);
        return telefone;
    }
}
