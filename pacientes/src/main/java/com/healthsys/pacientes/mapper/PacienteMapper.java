package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.PacienteRequestDTO;
import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.model.Genero;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.model.Sexo;
import com.healthsys.pacientes.model.Telefone;

import java.time.LocalDate;

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

    public static Paciente toModel(PacienteRequestDTO pacienteRequestDTO, Genero genero, Sexo sexo){
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteRequestDTO.getNome());
        paciente.setNomeSocial(pacienteRequestDTO.getNomeSocial());
        paciente.setDataNascimento(LocalDate.parse(pacienteRequestDTO.getDataNascimento()));
        paciente.setGenero(genero);
        paciente.setSexo(sexo);

        if (pacienteRequestDTO.getTelefone() != null && !pacienteRequestDTO.getTelefone().isEmpty()){
            Telefone telefone = new Telefone();
            telefone.setNumero(pacienteRequestDTO.getTelefone());
            telefone.setPaciente(paciente);
            paciente.setTelefone(telefone);
        }

        return paciente;

    }
}
