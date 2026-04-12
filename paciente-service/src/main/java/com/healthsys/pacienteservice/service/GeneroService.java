package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.GeneroRequestDTO;
import com.healthsys.pacienteservice.dto.GeneroResponseDTO;
import com.healthsys.pacienteservice.mapper.GeneroMapper;
import com.healthsys.pacienteservice.model.Genero;
import com.healthsys.pacienteservice.repository.GeneroRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class GeneroService {
    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public List<GeneroResponseDTO> getGeneros() {
        List<Genero> generos = generoRepository.findAll();
        return generos.stream()
                .map(GeneroMapper::toDTO)
                .toList();
    }

    public GeneroResponseDTO criarGenero(GeneroRequestDTO dto) {
        Genero novoGenero = GeneroMapper.toModel(dto);
        return GeneroMapper.toDTO(generoRepository.save(novoGenero));
    }

    public GeneroResponseDTO atualizarGenero(Integer id, GeneroRequestDTO dto) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado com ID: " + id));

        genero.setDescricao(dto.getDescricao());
        return GeneroMapper.toDTO(generoRepository.save(genero));
    }

    public void deletarGenero(Integer id) {
        generoRepository.deleteById(id);
    }
}
