package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.GeneroRequestDTO;
import com.healthsys.pacienteservice.dto.GeneroResponseDTO;
import com.healthsys.pacienteservice.mapper.GeneroMapper;
import com.healthsys.pacienteservice.model.Genero;
import com.healthsys.pacienteservice.repository.GeneroRepository;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict; // <-- Adicionado
import org.springframework.cache.annotation.Cacheable; // <-- Adicionado
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class GeneroService {
    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    // Busca a lista de gêneros diretamente do Redis se ela já estiver disponível
    @Cacheable(value = "generos_todos", key = "'lista'")
    public List<GeneroResponseDTO> getGeneros() {
        List<Genero> generos = generoRepository.findAll();
        return generos.stream()
                .map(GeneroMapper::toDTO)
                .toList();
    }

    // Remove a lista desatualizada do cache ao inserir um novo gênero
    @CacheEvict(value = "generos_todos", key = "'lista'")
    public GeneroResponseDTO criarGenero(GeneroRequestDTO dto) {
        Genero novoGenero = GeneroMapper.toModel(dto);
        return GeneroMapper.toDTO(generoRepository.save(novoGenero));
    }

    // Invalida o cache para que as alterações na descrição se reflitam na listagem geral
    @CacheEvict(value = "generos_todos", key = "'lista'")
    public GeneroResponseDTO atualizarGenero(Integer id, GeneroRequestDTO dto) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado com ID: " + id));

        genero.setDescricao(dto.getDescricao());
        return GeneroMapper.toDTO(generoRepository.save(genero));
    }

    // Remove a listagem em cache após excluir fisicamente o registro
    @CacheEvict(value = "generos_todos", key = "'lista'")
    public void deletarGenero(Integer id) {
        generoRepository.deleteById(id);
    }
}