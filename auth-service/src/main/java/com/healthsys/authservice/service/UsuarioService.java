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

    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> getUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Um usuario com esse e-mail ja existe");
        }

        Perfil perfil = perfilRepository.findById(usuarioRequestDTO.getPerfil())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Perfil nao encontrado com o ID: " + usuarioRequestDTO.getPerfil()));

        Especialidade especialidade = especialidadeRepository.findById(usuarioRequestDTO.getEspecialidade())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Especialidade nao encontrada com o ID: " + usuarioRequestDTO.getEspecialidade()));

        Usuario novoUsuario = UsuarioMapper.toModel(usuarioRequestDTO, perfil, especialidade);
        novoUsuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return UsuarioMapper.toDTO(usuarioSalvo);
    }

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
                            "Especialidade nao encontrada com o  ID: " + usuarioRequestDTO.getEspecialidade()));
            usuario.setEspecialidade(especialidade);
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuarioAtualizado);
    }

    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
