package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.PacienteVacinaRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteVacinaResponseDTO;
import com.healthsys.pacienteservice.exception.PacienteNotFoundException;
import com.healthsys.pacienteservice.mapper.PacienteVacinaMapper;
import com.healthsys.pacienteservice.model.Paciente;
import com.healthsys.pacienteservice.model.PacienteVacina;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.PacienteRepository;
import com.healthsys.pacienteservice.repository.PacienteVacinaRepository;
import com.healthsys.pacienteservice.repository.VacinaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PacienteVacinaService {

    private final PacienteVacinaRepository pacienteVacinaRepository;
    private final PacienteRepository pacienteRepository;
    private final VacinaRepository vacinaRepository;

    public PacienteVacinaService(
            PacienteVacinaRepository pacienteVacinaRepository,
            PacienteRepository pacienteRepository,
            VacinaRepository vacinaRepository
    ) {
        this.pacienteVacinaRepository = pacienteVacinaRepository;
        this.pacienteRepository = pacienteRepository;
        this.vacinaRepository = vacinaRepository;
    }

    public List<PacienteVacinaResponseDTO> getPacienteVacinaPorPaciente(UUID pacienteId) {
        pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + pacienteId));

        return pacienteVacinaRepository.findByPacienteId(pacienteId)
                .stream()
                .map(PacienteVacinaMapper::toDTO)
                .toList();
    }

    public PacienteVacinaResponseDTO vincular(PacienteVacinaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));

        Vacina vacina = vacinaRepository.findById(dto.getVacinaId())
                .orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada com ID: " + dto.getVacinaId()));

        PacienteVacina entidade = PacienteVacinaMapper.toModel(dto, paciente, vacina);
        return PacienteVacinaMapper.toDTO(pacienteVacinaRepository.save(entidade));
    }

    public PacienteVacinaResponseDTO atualizar(UUID id, PacienteVacinaRequestDTO dto) {
        PacienteVacina atual = pacienteVacinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vínculo não encontrado com ID: " + id));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + dto.getPacienteId()));

        Vacina vacina = vacinaRepository.findById(dto.getVacinaId())
                .orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada com ID: " + dto.getVacinaId()));

        atual.setPaciente(paciente);
        atual.setVacina(vacina);
        atual.setDataAplicacao(LocalDate.parse(dto.getDataAplicacao()));

        return PacienteVacinaMapper.toDTO(pacienteVacinaRepository.save(atual));
    }

    public void deletar(UUID id) {
        pacienteVacinaRepository.deleteById(id);
    }
}
