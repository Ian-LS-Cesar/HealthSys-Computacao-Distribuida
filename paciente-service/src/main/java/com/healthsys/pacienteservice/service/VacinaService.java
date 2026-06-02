package com.healthsys.pacienteservice.service;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.mapper.VacinaMapper;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.VacinaRepository;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict; // <-- Adicionado
import org.springframework.cache.annotation.CachePut;   // <-- Adicionado
import org.springframework.cache.annotation.Cacheable; // <-- Adicionado
import org.springframework.cache.annotation.Caching;   // <-- Adicionado
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

    // 1. Cache da listagem global de vacinas
    @Cacheable(value = "vacinas_todas", key = "'lista'")
    public List<VacinaResponseDTO> getVacinas() {
        return vacinaRepository.findAll()
                .stream()
                .map(VacinaMapper::toDTO)
                .toList();
    }

    // 2. Cache por ID individual da vacina
    @Cacheable(value = "vacina_individual", key = "#id")
    public VacinaResponseDTO getVacinaById(UUID id) {
        Vacina vacina = vacinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada com ID: " + id));
        return VacinaMapper.toDTO(vacina);
    }

    // 3. Criar invalida apenas a lista global para que ela seja reconstruída com a nova vacina
    @CacheEvict(value = "vacinas_todas", key = "'lista'")
    public VacinaResponseDTO criarVacina(VacinaRequestDTO dto) {
        Vacina novaVacina = VacinaMapper.toModel(dto);
        return VacinaMapper.toDTO(vacinaRepository.save(novaVacina));
    }

    // 4. Atualizar limpa a lista global e atualiza dinamicamente o cache do ID modificado usando @CachePut
    @Caching(
            put = { @CachePut(value = "vacina_individual", key = "#id") },
            evict = { @CacheEvict(value = "vacinas_todas", key = "'lista'") }
    )
    public VacinaResponseDTO atualizarVacina(UUID id, VacinaRequestDTO dto) {
        Vacina vacina = vacinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada com ID: " + id));

        vacina.setNome(dto.getNome());
        return VacinaMapper.toDTO(vacinaRepository.save(vacina));
    }

    // 5. Deletar remove o registro tanto do cache individual por ID quanto da lista global
    @Caching(evict = {
            @CacheEvict(value = "vacina_individual", key = "#id"),
            @CacheEvict(value = "vacinas_todas", key = "'lista'")
    })
    public void deletarVacina(UUID id) {
        vacinaRepository.deleteById(id);
    }
}