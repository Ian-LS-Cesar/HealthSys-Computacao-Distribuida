package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.TelefoneRequestDTO;
import com.healthsys.pacientes.dto.TelefoneResponseDTO;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.model.Telefone;

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
