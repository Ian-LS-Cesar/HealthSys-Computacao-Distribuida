package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.exception.PacienteNotFoundException;
import com.healthsys.pacienteservice.mapper.VacinaMapper;
import com.healthsys.pacienteservice.model.Paciente;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.PacienteRepository;
import com.healthsys.pacienteservice.repository.VacinaRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Service
public class VacinaService {
    private final VacinaRepository vacinaRepository;
    private final PacienteRepository pacienteRepository;

    public VacinaService(VacinaRepository vacinaRepository, PacienteRepository pacienteRepository) {
        this.vacinaRepository = vacinaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<VacinaResponseDTO> getVacinas() {
        List<Vacina> vacinas = vacinaRepository.findAll();
        return vacinas.stream()
                .map(VacinaMapper::toDTO)
                .toList();
    }

    public List<VacinaResponseDTO> getVacinasPorPaciente(UUID paciente){
        pacienteRepository.findById(paciente)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + paciente));
        List<Vacina> vacinas = vacinaRepository.findByPacienteId(paciente);
        return vacinas.stream()
                .map(VacinaMapper::toDTO)
                .toList();
    }

    public VacinaResponseDTO criarVacina(VacinaRequestDTO vacinaRequestDTO){
        Paciente paciente = pacienteRepository.findById(vacinaRequestDTO.getPaciente())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + vacinaRequestDTO.getPaciente()));
        Vacina novaVacina = VacinaMapper.toModel(vacinaRequestDTO, paciente);
        return VacinaMapper.toDTO(vacinaRepository.save(novaVacina));
    }

    public VacinaResponseDTO atualizarVacina(UUID id, VacinaRequestDTO vacinaRequestDTO){
        Vacina vacina = vacinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada com ID: " + id));
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com ID: " + vacinaRequestDTO.getPaciente()));
        vacina.setPaciente(paciente);
        vacina.setNome(vacinaRequestDTO.getNome());
        vacina.setNome(vacinaRequestDTO.getNome());
        vacina.setDataAplicacao(LocalDate.parse(vacinaRequestDTO.getDataAplicacao()));
        return VacinaMapper.toDTO(vacinaRepository.save(vacina));
    }

    public void deletarVacina(UUID id){
        vacinaRepository.deleteById(id);
    }
}
