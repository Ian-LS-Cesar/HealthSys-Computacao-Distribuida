package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.EnderecoResponseDTO;
import com.healthsys.pacientes.dto.PacienteRequestDTO;
import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.model.*;

import java.time.LocalDate;

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

        if (paciente.getEnderecos() != null) {
            pacienteDTO.setEnderecos(
                    paciente.getEnderecos().stream()
                            .map(e -> {
                                EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO();
                                enderecoDTO.setId(e.getId());
                                enderecoDTO.setPaciente(e.getPaciente().getId());
                                enderecoDTO.setLogradouro(e.getLogradouro());
                                enderecoDTO.setNumero(e.getNumero());
                                enderecoDTO.setComplemento(e.getComplemento());
                                enderecoDTO.setBairro(e.getBairro());
                                enderecoDTO.setCidade(e.getCidade());
                                enderecoDTO.setUf(e.getUf());
                                enderecoDTO.setCep(e.getCep());
                                return enderecoDTO;
                            })
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
        paciente.setCpf(pacienteRequestDTO.getCpf());
        paciente.setGenero(genero);
        paciente.setSexo(sexo);

        return paciente;
    }
}
