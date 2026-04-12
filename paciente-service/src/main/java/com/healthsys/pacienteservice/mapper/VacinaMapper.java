package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.model.Paciente;
import com.healthsys.pacienteservice.model.Vacina;

import java.time.LocalDate;

public class VacinaMapper {
    public static VacinaResponseDTO toDTO(Vacina vacina) {
        VacinaResponseDTO vacinaResponseDTO = new VacinaResponseDTO();
        vacinaResponseDTO.setId(vacina.getId());
        vacinaResponseDTO.setPaciente(vacina.getPaciente().getId());
        vacinaResponseDTO.setNome(vacina.getNome());
        vacinaResponseDTO.setDataAplicacao(vacina.getDataAplicacao().toString());
        return vacinaResponseDTO;
    }

    public static Vacina toModel(VacinaRequestDTO vacinaRequestDTO, Paciente paciente) {
        Vacina vacina = new Vacina();
        vacina.setPaciente(paciente);
        vacina.setNome(vacinaRequestDTO.getNome());
        vacina.setDataAplicacao(LocalDate.parse(vacinaRequestDTO.getDataAplicacao()));
        return vacina;
    }
}
