package com.healthsys.triagemservice.service;

import com.healthsys.triagemservice.dto.RiscoRequestDTO;
import com.healthsys.triagemservice.dto.RiscoResponseDTO;
import com.healthsys.triagemservice.mapper.RiscoMapper;
import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.repository.RiscoRepository;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class RiscoService {
    private final RiscoRepository riscoRepository;

    public RiscoService(RiscoRepository riscoRepository) {
        this.riscoRepository = riscoRepository;
    }

    // 1. Busca a lista de riscos. Se já estiver no cache, não toca no banco.
    @Cacheable(value = "riscos_todos", key = "'lista'")
    public List<RiscoResponseDTO> getRiscos(){
        List<Risco> riscos = riscoRepository.findAll();
        return riscos.stream()
                .map(RiscoMapper::toDTO)
                .toList();
    }

    // 2. Criar um novo risco invalida o cache antigo da lista
    @CacheEvict(value = "riscos_todos", key = "'lista'")
    public RiscoResponseDTO criarRisco(RiscoRequestDTO dto){
        Risco novoRisco = RiscoMapper.toModel(dto);
        return RiscoMapper.toDTO(riscoRepository.save(novoRisco));
    }

    // 3. Atualizar um risco invalida a lista desatualizada no cache
    @CacheEvict(value = "riscos_todos", key = "'lista'")
    public RiscoResponseDTO atualizarRisco(Integer id, RiscoRequestDTO dto){
        Risco risco = riscoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Risco não encontrado com ID: " + id));

        risco.setDescricao(dto.getDescricao());
        return RiscoMapper.toDTO(riscoRepository.save(risco));
    }

    // 4. Deletar um risco também precisa limpar o cache da lista
    @CacheEvict(value = "riscos_todos", key = "'lista'")
    public void deletarRisco(Integer id){
        riscoRepository.deleteById(id);
    }
}