package com.healthsys.pacientes.service;

import com.healthsys.pacientes.dto.TipoAtendimentoRequestDTO;
import com.healthsys.pacientes.dto.TipoAtendimentoResponseDTO;
import com.healthsys.pacientes.mapper.TipoAtendimentoMapper;
import com.healthsys.pacientes.model.TipoAtendimento;
import com.healthsys.pacientes.repository.TipoAtendimentoRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class TipoAtendimentoService {
    private final TipoAtendimentoRepository tipoAtendimentoRepository;

    public TipoAtendimentoService(TipoAtendimentoRepository tipoAtendimentoRepository) {
        this.tipoAtendimentoRepository = tipoAtendimentoRepository;
    }

    public List<TipoAtendimentoResponseDTO> getTiposAtendimento() {
        List<TipoAtendimento> tiposAtendimento = tipoAtendimentoRepository.findAll();
        return tiposAtendimento.stream()
                .map(TipoAtendimentoMapper::toDTO)
                .toList();
    }

    public TipoAtendimentoResponseDTO getTipoAtendimentoById(Integer id) {
        TipoAtendimento tipoAtendimento = tipoAtendimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Atendimento não encontrado com ID: " + id));
        return TipoAtendimentoMapper.toDTO(tipoAtendimento);
    }

    public TipoAtendimentoResponseDTO criarTipoAtendimento(TipoAtendimentoRequestDTO tipoAtendimentoRequestDTO) {
        TipoAtendimento novoTipoAtendimento = TipoAtendimentoMapper.toModel(tipoAtendimentoRequestDTO);
        return TipoAtendimentoMapper.toDTO(tipoAtendimentoRepository.save(novoTipoAtendimento));
    }

    public TipoAtendimentoResponseDTO atualizarTipoAtendimento(Integer id, TipoAtendimentoRequestDTO tipoAtendimentoRequestDTO) {
        TipoAtendimento tipoAtendimento = tipoAtendimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Atendimento não encontrado com ID: " + id));

        tipoAtendimento.setDescricao(tipoAtendimentoRequestDTO.getDescricao());
        return TipoAtendimentoMapper.toDTO(tipoAtendimentoRepository.save(tipoAtendimento));
    }

    public void deletarTipoAtendimento(Integer id) {
        tipoAtendimentoRepository.deleteById(id);
    }
}
