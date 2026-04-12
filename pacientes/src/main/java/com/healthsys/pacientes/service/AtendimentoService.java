package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.AtendimentoRequestDTO;
import com.healthsys.pacientes.dto.AtendimentoResponseDTO;
import com.healthsys.pacientes.exception.PacienteNotFoundException;
import com.healthsys.pacientes.mapper.AtendimentoMapper;
import com.healthsys.pacientes.model.Atendimento;
import com.healthsys.pacientes.model.Paciente;
import com.healthsys.pacientes.model.TipoAtendimento;
import com.healthsys.pacientes.repository.AtendimentoRepository;
import com.healthsys.pacientes.repository.PacienteRepository;
import com.healthsys.pacientes.repository.TipoAtendimentoRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Setter
@Service
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;
    private final PacienteRepository pacienteRepository;
    private final TipoAtendimentoRepository tipoAtendimentoRepository;

    public AtendimentoService(
            AtendimentoRepository atendimentoRepository,
            PacienteRepository pacienteRepository,
            TipoAtendimentoRepository tipoAtendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.tipoAtendimentoRepository = tipoAtendimentoRepository;
    }

    public List<AtendimentoResponseDTO> getAtendimentos() {
        List<Atendimento> atendimentos = atendimentoRepository.findAll();
        return atendimentos.stream()
                .map(AtendimentoMapper::toDTO)
                .toList();
    }

    public List<AtendimentoResponseDTO> getAtendimentosPorPaciente(UUID pacienteId) {
        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + pacienteId));

        List<Atendimento> atendimentos = atendimentoRepository.findByPacienteId(pacienteId);
        return atendimentos.stream()
                .map(AtendimentoMapper::toDTO)
                .toList();
    }

    public AtendimentoResponseDTO criarAtendimento(AtendimentoRequestDTO atendimentoRequestDTO) {
        // Validar se paciente existe
        Paciente paciente = pacienteRepository.findById(atendimentoRequestDTO.getPaciente())
                .orElseThrow(() -> new PacienteNotFoundException(
                        "Paciente não encontrado com ID: " + atendimentoRequestDTO.getPaciente()));

        // Validar se tipo de atendimento existe
        TipoAtendimento tipoAtendimento = tipoAtendimentoRepository.findById(atendimentoRequestDTO.getTipoAtendimento())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tipo de Atendimento não encontrado com ID: " + atendimentoRequestDTO.getTipoAtendimento()));

        Atendimento novoAtendimento = AtendimentoMapper.toModel(atendimentoRequestDTO, paciente, tipoAtendimento);
        return AtendimentoMapper.toDTO(atendimentoRepository.save(novoAtendimento));
    }

    public AtendimentoResponseDTO atualizarAtendimento(UUID id, AtendimentoRequestDTO atendimentoRequestDTO) {
        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Atendimento não encontrado com ID: " + id));

        // Validar se paciente existe
        Paciente paciente = pacienteRepository.findById(atendimentoRequestDTO.getPaciente())
                .orElseThrow(() -> new PacienteNotFoundException(
                        "Paciente não encontrado com ID: " + atendimentoRequestDTO.getPaciente()));

        // Validar se tipo de atendimento existe
        TipoAtendimento tipoAtendimento = tipoAtendimentoRepository.findById(atendimentoRequestDTO.getTipoAtendimento())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tipo de Atendimento não encontrado com ID: " + atendimentoRequestDTO.getTipoAtendimento()));

        atendimento.setPaciente(paciente);
        atendimento.setTipoAtendimento(tipoAtendimento);
        atendimento.setObservacao(atendimentoRequestDTO.getObservacao());
        atendimento.setDataAtendimento(java.time.LocalDate.parse(atendimentoRequestDTO.getDataAtendimento()));

        return AtendimentoMapper.toDTO(atendimentoRepository.save(atendimento));
    }

    public void deletarAtendimento(UUID id) {
        atendimentoRepository.deleteById(id);
    }
}
