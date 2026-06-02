package com.healthsys.authservice.service;

import com.healthsys.authservice.dto.UsuarioRequestDTO;
import com.healthsys.authservice.dto.UsuarioResponseDTO;
import com.healthsys.authservice.exception.EmailAlreadyExistsException;
import com.healthsys.authservice.exception.UsuarioNotFoundException;
import com.healthsys.authservice.mapper.UsuarioMapper;
import com.healthsys.authservice.model.Especialidade;
import com.healthsys.authservice.model.Perfil;
import com.healthsys.authservice.model.Usuario;
import com.healthsys.authservice.repository.EspecialidadeRepository;
import com.healthsys.authservice.repository.PerfilRepository;
import com.healthsys.authservice.repository.UsuarioRepository;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Setter
@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PerfilRepository perfilRepository,
            EspecialidadeRepository especialidadeRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Cacheable(value = "usuarios", key="#email")
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> getUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> getUsuariosByPerfilId(Integer perfilId) {
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new IllegalArgumentException("Perfil nao encontrado com o ID: " + perfilId));

        return usuarioRepository.findAll().stream()
                .filter(u -> u.getPerfil() != null && u.getPerfil().getId() == perfil.getId())
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> getUsuariosByEspecialidadeId(Integer especialidadeId) {
        Especialidade especialidade = especialidadeRepository.findById(especialidadeId)
                .orElseThrow(() -> new IllegalArgumentException("Especialidade nao encontrada com o ID: " + especialidadeId));

        return usuarioRepository.findAll().stream()
                .filter(u -> u.getEspecialidade() != null && u.getEspecialidade().getId() == especialidade.getId())
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public UsuarioResponseDTO getUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario nao encontrado com email: " + email));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO getUsuarioById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com o ID: " + id));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Um usuario com esse e-mail ja existe");
        }

        Perfil perfil = perfilRepository.findById(usuarioRequestDTO.getPerfil())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Perfil nao encontrado com o ID: " + usuarioRequestDTO.getPerfil()));

        Especialidade especialidade = null;
        if (usuarioRequestDTO.getEspecialidade() != null) {
            especialidade = especialidadeRepository.findById(usuarioRequestDTO.getEspecialidade())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Especialidade nao encontrada com o ID: " + usuarioRequestDTO.getEspecialidade()));
        }
        Usuario novoUsuario = UsuarioMapper.toModel(usuarioRequestDTO, perfil, especialidade);
        novoUsuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return UsuarioMapper.toDTO(usuarioSalvo);
    }

    @CacheEvict(value= "usuarios", allEntries = true)
    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario nao encontrado com ID: " + id));

        if (usuarioRequestDTO.getEmail() != null && !usuarioRequestDTO.getEmail().isBlank()) {
            if (usuarioRepository.existsByEmailAndIdNot(usuarioRequestDTO.getEmail(), id)) {
                throw new EmailAlreadyExistsException(
                        "Um usuario com esse endereco de e-mail ja existe: " + usuarioRequestDTO.getEmail()
                );
            }
            usuario.setEmail(usuarioRequestDTO.getEmail());
        }

        if (usuarioRequestDTO.getNome() != null && !usuarioRequestDTO.getNome().isBlank()) {
            usuario.setNome(usuarioRequestDTO.getNome());
        }

        if (usuarioRequestDTO.getDataNascimento() != null && !usuarioRequestDTO.getDataNascimento().isBlank()) {
            usuario.setDataNascimento(LocalDate.parse(usuarioRequestDTO.getDataNascimento()));
        }

        if (usuarioRequestDTO.getSenha() != null && !usuarioRequestDTO.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        }

        if (usuarioRequestDTO.getPerfil() != null) {
            Perfil perfil = perfilRepository.findById(usuarioRequestDTO.getPerfil())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Perfil nao encontrado com o ID: " + usuarioRequestDTO.getPerfil()));
            usuario.setPerfil(perfil);
        }

        if (usuarioRequestDTO.getEspecialidade() != null) {
            Especialidade especialidade = especialidadeRepository.findById(usuarioRequestDTO.getEspecialidade())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Especialidade nao encontrada com o ID: " + usuarioRequestDTO.getEspecialidade()));
            usuario.setEspecialidade(especialidade);
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuarioAtualizado);
    }

    @CacheEvict(value ="usuarios", allEntries = true)
    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }
}