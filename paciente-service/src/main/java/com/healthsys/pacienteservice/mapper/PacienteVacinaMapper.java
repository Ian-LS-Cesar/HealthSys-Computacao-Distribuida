package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.PacienteVacinaRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteVacinaResponseDTO;
import com.healthsys.pacienteservice.model.Paciente;
import com.healthsys.pacienteservice.model.PacienteVacina;
import com.healthsys.pacienteservice.model.Vacina;

import java.time.LocalDate;

public class PacienteVacinaMapper {

    public static PacienteVacinaResponseDTO toDTO(PacienteVacina pacienteVacina) {
        PacienteVacinaResponseDTO dto = new PacienteVacinaResponseDTO();
        dto.setId(pacienteVacina.getId());
        dto.setPacienteId(pacienteVacina.getPaciente().getId());
        dto.setVacinaId(pacienteVacina.getVacina().getId());
        dto.setNomeVacina(pacienteVacina.getVacina().getNome());
        dto.setDataAplicacao(pacienteVacina.getDataAplicacao().toString());
        return dto;
    }

    public static PacienteVacina toModel(
            PacienteVacinaRequestDTO dto,
            Paciente paciente,
            Vacina vacina
    ) {
        PacienteVacina pacienteVacina = new PacienteVacina();
        pacienteVacina.setPaciente(paciente);
        pacienteVacina.setVacina(vacina);
        pacienteVacina.setDataAplicacao(LocalDate.parse(dto.getDataAplicacao()));
        return pacienteVacina;
    }
}