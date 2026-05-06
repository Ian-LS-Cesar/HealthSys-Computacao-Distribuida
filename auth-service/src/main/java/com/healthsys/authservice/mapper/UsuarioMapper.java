package com.healthsys.authservice.mapper;

import com.healthsys.authservice.dto.UsuarioRequestDTO;
import com.healthsys.authservice.dto.UsuarioResponseDTO;
import com.healthsys.authservice.model.Especialidade;
import com.healthsys.authservice.model.Perfil;
import com.healthsys.authservice.model.Usuario;

import java.time.LocalDate;

public class UsuarioMapper {

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setId(usuario.getId().toString());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setDataNascimento(usuario.getDataNascimento().toString());

        // Se quiser manter, deixa. Em produção o ideal é NÃO expor senha no response.
        usuarioDTO.setSenha(usuario.getSenha());

        if (usuario.getPerfil() != null) {
            usuarioDTO.setPerfil(usuario.getPerfil().getDescricao());
        }

        if (usuario.getEspecialidade() != null) {
            usuarioDTO.setEspecialidade(usuario.getEspecialidade().getDescricao());
        }

        return usuarioDTO;
    }

    public static Usuario toModel(UsuarioRequestDTO usuarioDTO, Perfil perfil, Especialidade especialidade) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setDataNascimento(LocalDate.parse(usuarioDTO.getDataNascimento()));
        usuario.setPerfil(perfil);
        usuario.setEspecialidade(especialidade);
        return usuario;
    }
}
