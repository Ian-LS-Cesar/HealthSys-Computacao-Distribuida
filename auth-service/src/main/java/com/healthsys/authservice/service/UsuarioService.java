package com.healthsys.authservice.service;

import com.healthsys.authservice.dto.UsuarioRequestDTO;
import com.healthsys.authservice.dto.UsuarioResponseDTO;
import com.healthsys.authservice.exception.EmailAlreadyExistsException;
import com.healthsys.authservice.exception.UsuarioNotFoundException;
import com.healthsys.authservice.mapper.UsuarioMapper;
import com.healthsys.authservice.model.Perfil;
import com.healthsys.authservice.model.Usuario;
import com.healthsys.authservice.repository.PerfilRepository;
import com.healthsys.authservice.repository.UsuarioRepository;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PerfilRepository perfilRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> getUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Um usuário com esse e-mail já existe");
        }

        Perfil perfil = perfilRepository.findById(usuarioRequestDTO.getPerfil())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Perfil não encontrado com o ID: " + usuarioRequestDTO.getPerfil()));

        Usuario novoUsuario = UsuarioMapper.toModel(usuarioRequestDTO, perfil);
        novoUsuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return UsuarioMapper.toDTO(usuarioSalvo);
    }

    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado com ID: " + id));

        // Verifica e-mail duplicado ignorando o próprio registro que está sendo atualizado
        if (usuarioRepository.existsByEmailAndIdNot(usuarioRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException(
                    "Um usuário com esse endereço de e-mail já existe: " + usuarioRequestDTO.getEmail()
            );
        }

        Perfil perfil = perfilRepository.findById(usuarioRequestDTO.getPerfil())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Perfil não encontrado com o ID: " + usuarioRequestDTO.getPerfil()));

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setDataNascimento(LocalDate.parse(usuarioRequestDTO.getDataNascimento()));
        usuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        usuario.setPerfil(perfil);

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuarioAtualizado);
    }

    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
