package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.dto.SintomaRequestDTO;
import com.healthsys.triagemservice.dto.SintomaResponseDTO;
import com.healthsys.triagemservice.mapper.SintomaMapper;
import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.model.Sintoma;
import com.healthsys.triagemservice.repository.RiscoRepository;
import com.healthsys.triagemservice.repository.SintomaRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class SintomaService {
    private final SintomaRepository sintomaRepository;
    private final RiscoRepository riscoRepository;

    public SintomaService(SintomaRepository sintomaRepository, RiscoRepository riscoRepository) {
        this.sintomaRepository = sintomaRepository;
        this.riscoRepository = riscoRepository;
    }

    public List<SintomaResponseDTO> getSintomas() {
        List<Sintoma> sintomas = sintomaRepository.findAll();
        return sintomas.stream()
                .map(SintomaMapper::toDTO)
                .toList();
    }

    public SintomaResponseDTO criarSintoma(SintomaRequestDTO dto) {
        Risco risco = riscoRepository.findById(dto.getRisco())
                .orElseThrow(() -> new IllegalArgumentException("Risco não encontrado com ID: " + dto.getRisco()));

        Sintoma novoSintoma = SintomaMapper.toModel(dto, risco);
        return SintomaMapper.toDTO(sintomaRepository.save(novoSintoma));
    }

    public SintomaResponseDTO atualizarSintoma(Integer id,  SintomaRequestDTO dto) {
        Sintoma sintoma = sintomaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sintoma não encontrado com ID: " + id));

        Risco risco = riscoRepository.findById(dto.getRisco())
                .orElseThrow(() -> new IllegalArgumentException("Risco não encontrado com ID: " + dto.getRisco()));

        sintoma.setDescricao(dto.getDescricao());
        sintoma.setRisco(risco);
        return SintomaMapper.toDTO(sintomaRepository.save(sintoma));
    }

    public void deletarSintoma(Integer id) {
        sintomaRepository.deleteById(id);
    }
}
