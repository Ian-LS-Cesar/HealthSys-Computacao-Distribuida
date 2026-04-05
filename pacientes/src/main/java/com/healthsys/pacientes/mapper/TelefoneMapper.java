package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.TelefoneResponseDTO;
import com.healthsys.pacientes.model.Telefone;

public class TelefoneMapper {
    public static TelefoneResponseDTO toDTO(Telefone telefone) {
        TelefoneResponseDTO telefoneResponseDTO = new TelefoneResponseDTO();
        telefoneResponseDTO.setId(telefone.getId());
        telefoneResponseDTO.setNumero(telefone.getNumero());
        return telefoneResponseDTO;
    }
}
