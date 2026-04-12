package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.AlergiaRequestDTO;
import com.healthsys.pacientes.dto.AlergiaResponseDTO;
import com.healthsys.pacientes.exception.PacienteNotFoundException;
import com.healthsys.pacientes.mapper.AlergiaMapper;
import com.healthsys.pacientes.model.Alergia;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.repository.AlergiaRepository;
import com.healthsys.pacientes.repository.PacienteRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Setter
@Service
public class AlergiaService {
    private final AlergiaRepository alergiaRepository;
    private final PacienteRepository pacienteRepository;

    public AlergiaService(AlergiaRepository alergiaRepository, PacienteRepository pacienteRepository) {
        this.alergiaRepository = alergiaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<AlergiaResponseDTO> getAlergias() {
        List<Alergia> alergias = alergiaRepository.findAll();
        return alergias.stream()
                .map(AlergiaMapper::toDTO)
                .toList();
    }

    public List<AlergiaResponseDTO> getAlergiasPorPaciente(UUID pacienteId) {
        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + pacienteId));

        List<Alergia> alergias = alergiaRepository.findByPacienteId(pacienteId);
        return alergias.stream()
                .map(AlergiaMapper::toDTO)
                .toList();
    }

    public AlergiaResponseDTO criarAlergia(AlergiaRequestDTO alergiaRequestDTO) {
        Paciente paciente = pacienteRepository.findById(alergiaRequestDTO.getPaciente())
                .orElseThrow(() -> new PacienteNotFoundException(
                        "Paciente não encontrado com ID: " + alergiaRequestDTO.getPaciente()));

        Alergia novaAlergia = AlergiaMapper.toModel(alergiaRequestDTO, paciente);
        return AlergiaMapper.toDTO(alergiaRepository.save(novaAlergia));
    }

    public AlergiaResponseDTO atualizarAlergia(Integer id, AlergiaRequestDTO alergiaRequestDTO) {
        Alergia alergia = alergiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alergia não encontrada com ID: " + id));

        Paciente paciente = pacienteRepository.findById(alergiaRequestDTO.getPaciente())
                .orElseThrow(() -> new PacienteNotFoundException(
                        "Paciente não encontrado com ID: " + alergiaRequestDTO.getPaciente()));

        alergia.setDescricao(alergiaRequestDTO.getDescricao());
        alergia.setPaciente(paciente);
        return AlergiaMapper.toDTO(alergiaRepository.save(alergia));
    }

    public void deletarAlergia(Integer id) {
        alergiaRepository.deleteById(id);
    }
}
