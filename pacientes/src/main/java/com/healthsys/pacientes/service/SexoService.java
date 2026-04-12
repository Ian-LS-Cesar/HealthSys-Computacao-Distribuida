package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.SexoRequestDTO;
import com.healthsys.pacientes.dto.SexoResponseDTO;
import com.healthsys.pacientes.mapper.SexoMapper;
import com.healthsys.pacientes.model.Sexo;
import com.healthsys.pacientes.repository.SexoRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class SexoService {
    private final SexoRepository sexoRepository;

    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    public List<SexoResponseDTO> getSexos() {
        List<Sexo> sexos = sexoRepository.findAll();
        return sexos.stream()
                .map(SexoMapper::toDTO)
                .toList();
    }

    public SexoResponseDTO criarSexo(SexoRequestDTO dto) {
        Sexo novoSexo = SexoMapper.toModel(dto);
        return SexoMapper.toDTO(sexoRepository.save(novoSexo));
    }

    public SexoResponseDTO atualizarSexo(Integer id, SexoRequestDTO dto) {
        Sexo sexo = sexoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sexo não encontrado com ID: " + id));

        sexo.setDescricao(dto.getDescricao());
        return SexoMapper.toDTO(sexoRepository.save(sexo));
    }

    public void deletarSexo(Integer id) {
        sexoRepository.deleteById(id);
    }
}
