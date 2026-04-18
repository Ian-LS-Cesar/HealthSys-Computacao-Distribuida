package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.mapper.VacinaMapper;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.VacinaRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Setter
@Service
public class VacinaService {
    private final VacinaRepository vacinaRepository;

    public VacinaService(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    public List<VacinaResponseDTO> getVacinas() {
        return vacinaRepository.findAll()
                .stream()
                .map(VacinaMapper::toDTO)
                .toList();
    }

    public VacinaResponseDTO getVacinaById(UUID id) {
        Vacina vacina = vacinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada com ID: " + id));
        return VacinaMapper.toDTO(vacina);
    }

    public VacinaResponseDTO criarVacina(VacinaRequestDTO dto) {
        Vacina novaVacina = VacinaMapper.toModel(dto);
        return VacinaMapper.toDTO(vacinaRepository.save(novaVacina));
    }

    public VacinaResponseDTO atualizarVacina(UUID id, VacinaRequestDTO dto) {
        Vacina vacina = vacinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada com ID: " + id));

        vacina.setNome(dto.getNome());
        return VacinaMapper.toDTO(vacinaRepository.save(vacina));
    }

    public void deletarVacina(UUID id) {
        vacinaRepository.deleteById(id);
    }
}