package com.healthsys.pacienteservice.mapper;

import com.healthsys.pacienteservice.dto.PacienteRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteResponseDTO;
import com.healthsys.pacienteservice.model.*;

import java.time.LocalDate;
import java.util.List;

public class PacienteMapper {
    public static PacienteResponseDTO toDTO(Paciente paciente) {
        PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
        pacienteDTO.setId(paciente.getId().toString());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setNomeSocial(paciente.getNomeSocial());
        pacienteDTO.setDataNascimento(paciente.getDataNascimento().toString());
        pacienteDTO.setCpf(paciente.getCpf());

        if (paciente.getGenero() != null) {
            pacienteDTO.setGenero(paciente.getGenero().getDescricao());
        }

        if (paciente.getSexo() != null) {
            pacienteDTO.setSexo(paciente.getSexo().getDescricao());
        }

        if (paciente.getTelefones() != null) {
            pacienteDTO.setTelefones(
                    paciente.getTelefones().stream()
                            .map(Telefone::getNumero)
                            .toList()
            );
        }

        if (paciente.getComorbidades() != null) {
            pacienteDTO.setComorbidades(
                    paciente.getComorbidades().stream()
                            .map(Comorbidade::getDescricao)
                            .toList()
            );
        }

        if (paciente.getEnderecos() != null) {
            pacienteDTO.setEnderecos(
                    paciente.getEnderecos().stream()
                            .map(EnderecoMapper::toDTO)
                            .toList()
            );
        }

        if (paciente.getAlergias() != null) {
            pacienteDTO.setAlergias(
                    paciente.getAlergias().stream()
                            .map(Alergia::getDescricao)
                            .toList()
            );
        }

        return pacienteDTO;
    }

    public static Paciente toModel(
            PacienteRequestDTO pacienteRequestDTO,
            Genero genero,
            Sexo sexo
    ) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteRequestDTO.getNome());
        paciente.setNomeSocial(pacienteRequestDTO.getNomeSocial());
        paciente.setDataNascimento(LocalDate.parse(pacienteRequestDTO.getDataNascimento()));
        paciente.setCpf(pacienteRequestDTO.getCpf().replaceAll("\\D", ""));
        paciente.setGenero(genero);
        paciente.setSexo(sexo);

        return paciente;
    }
}