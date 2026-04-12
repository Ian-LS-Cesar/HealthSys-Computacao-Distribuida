package com.healthsys.pacientes.mapper;

import com.healthsys.pacientes.dto.EnderecoRequestDTO;
import com.healthsys.pacientes.dto.EnderecoResponseDTO;
import com.healthsys.pacientes.model.Endereco;
import com.healthsys.pacientes.model.Paciente;

public class EnderecoMapper {
    public static EnderecoResponseDTO toDTO(Endereco endereco) {
        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();
        enderecoResponseDTO.setId(endereco.getId());
        enderecoResponseDTO.setPaciente(endereco.getPaciente().getId());
        enderecoResponseDTO.setLogradouro(endereco.getLogradouro());
        enderecoResponseDTO.setNumero(endereco.getNumero());
        enderecoResponseDTO.setComplemento(endereco.getComplemento());
        enderecoResponseDTO.setBairro(endereco.getBairro());
        enderecoResponseDTO.setCidade(endereco.getCidade());
        enderecoResponseDTO.setUf(endereco.getUf());
        enderecoResponseDTO.setCep(endereco.getCep());
        return enderecoResponseDTO;
    }

    public static Endereco toModel(EnderecoRequestDTO enderecoRequestDTO, Paciente paciente) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoRequestDTO.getLogradouro());
        endereco.setNumero(enderecoRequestDTO.getNumero());
        endereco.setComplemento(enderecoRequestDTO.getComplemento());
        endereco.setBairro(enderecoRequestDTO.getBairro());
        endereco.setCidade(enderecoRequestDTO.getCidade());
        endereco.setUf(enderecoRequestDTO.getUf());
        endereco.setCep(enderecoRequestDTO.getCep());
        endereco.setPaciente(paciente);
        return endereco;
    }
}
