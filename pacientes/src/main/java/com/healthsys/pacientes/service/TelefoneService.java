package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.TelefoneRequestDTO;
import com.healthsys.pacientes.dto.TelefoneResponseDTO;
import com.healthsys.pacientes.exception.PacienteNotFoundException;
import com.healthsys.pacientes.mapper.TelefoneMapper;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.model.Telefone;
import com.healthsys.pacientes.repository.PacienteRepository;
import com.healthsys.pacientes.repository.TelefoneRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Setter
@Service
public class TelefoneService {
    private final TelefoneRepository telefoneRepository;
    private final PacienteRepository pacienteRepository;

    public TelefoneService(TelefoneRepository telefoneRepository, PacienteRepository pacienteRepository) {
        this.telefoneRepository = telefoneRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<TelefoneResponseDTO> getTelefones() {
        List<Telefone> telefones = telefoneRepository.findAll();
        return telefones.stream()
                .map(TelefoneMapper::toDTO)
                .toList();
    }

    public List<TelefoneResponseDTO> getTelefonesPorPaciente(UUID pacienteId) {
        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + pacienteId));

        List<Telefone> telefones = telefoneRepository.findByPacienteId(pacienteId);
        return telefones.stream()
                .map(TelefoneMapper::toDTO)
                .toList();
    }

    public TelefoneResponseDTO criarTelefone(TelefoneRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPaciente())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + dto.getPaciente()));

        Telefone novoTelefone = TelefoneMapper.toModel(dto, paciente);
        return TelefoneMapper.toDTO(telefoneRepository.save(novoTelefone));
    }

    public TelefoneResponseDTO atualizarTelefone(Integer id, TelefoneRequestDTO dto) {
        Telefone telefone = telefoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Telefone não encontrado com ID: " + id));

        Paciente paciente = pacienteRepository.findById(dto.getPaciente())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + dto.getPaciente()));

        telefone.setNumero(dto.getNumero());
        telefone.setPaciente(paciente);
        return TelefoneMapper.toDTO(telefoneRepository.save(telefone));
    }

    public void deletarTelefone(Integer id) {
        telefoneRepository.deleteById(id);
    }
}
