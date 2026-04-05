package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.model.Paciente;

public class PacienteMapper {
    public static PacienteResponseDTO toDTO(Paciente paciente){
        PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
        pacienteDTO.setId(paciente.getId().toString());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setNomeSocial(paciente.getNomeSocial());
        pacienteDTO.setDataNascimento(paciente.getDataNascimento().toString());

        if (paciente.getGenero() != null){
            pacienteDTO.setGenero(paciente.getGenero().getDescricao());
        }

        if (paciente.getSexo() != null){
            pacienteDTO.setSexo(paciente.getSexo().getDescricao());
        }

        if (paciente.getTelefone() != null){
            pacienteDTO.setTelefone(paciente.getTelefone().getNumero());
        }

        return pacienteDTO;
    }
}
