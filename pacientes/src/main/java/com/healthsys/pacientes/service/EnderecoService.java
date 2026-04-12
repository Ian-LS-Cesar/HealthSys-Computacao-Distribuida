package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.EnderecoRequestDTO;
import com.healthsys.pacientes.dto.EnderecoResponseDTO;
import com.healthsys.pacientes.exception.PacienteNotFoundException;
import com.healthsys.pacientes.mapper.EnderecoMapper;
import com.healthsys.pacientes.model.Endereco;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.repository.EnderecoRepository;
import com.healthsys.pacientes.repository.PacienteRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Setter
@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PacienteRepository pacienteRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PacienteRepository pacienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<EnderecoResponseDTO> getEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream()
                .map(EnderecoMapper::toDTO)
                .toList();
    }

    public List<EnderecoResponseDTO> getEnderecosPorPaciente(UUID pacienteId) {
        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + pacienteId));

        List<Endereco> enderecos = enderecoRepository.findByPacienteId(pacienteId);
        return enderecos.stream()
                .map(EnderecoMapper::toDTO)
                .toList();
    }

    public EnderecoResponseDTO criarEndereco(UUID pacienteId, EnderecoRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + pacienteId));

        Endereco novoEndereco = EnderecoMapper.toModel(dto, paciente);
        return EnderecoMapper.toDTO(enderecoRepository.save(novoEndereco));
    }

    public EnderecoResponseDTO atualizarEndereco(Integer id, EnderecoRequestDTO dto) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado com ID: " + id));

        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setUf(dto.getUf());
        endereco.setCep(dto.getCep());

        return EnderecoMapper.toDTO(enderecoRepository.save(endereco));
    }

    public void deletarEndereco(Integer id) {
        enderecoRepository.deleteById(id);
    }
}
