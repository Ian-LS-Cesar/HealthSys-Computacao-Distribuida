package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.model.Vacina;

public class VacinaMapper {
    public static VacinaResponseDTO toDTO(Vacina vacina) {
        VacinaResponseDTO vacinaResponseDTO = new VacinaResponseDTO();
        vacinaResponseDTO.setId(vacina.getId());
        vacinaResponseDTO.setNome(vacina.getNome());
        return vacinaResponseDTO;
    }

    public static Vacina toModel(VacinaRequestDTO vacinaRequestDTO) {
        Vacina vacina = new Vacina();
        vacina.setNome(vacinaRequestDTO.getNome());
        return vacina;
    }
}
