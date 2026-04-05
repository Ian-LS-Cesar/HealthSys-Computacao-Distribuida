package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.TelefoneDTO;
import com.healthsys.pacientes.model.Telefone;

public class TelefoneMapper {
    public static TelefoneDTO toDTO(Telefone telefone) {
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefoneDTO.setId(telefone.getId());
        telefoneDTO.setNumero(telefone.getNumero());
        return telefoneDTO;
    }
}
