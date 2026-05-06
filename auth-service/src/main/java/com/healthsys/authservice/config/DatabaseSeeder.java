package com.healthsys.authservice.config;

import com.healthsys.authservice.model.Especialidade;
import com.healthsys.authservice.model.Perfil;
import com.healthsys.authservice.model.Usuario;
import com.healthsys.authservice.repository.EspecialidadeRepository;
import com.healthsys.authservice.repository.PerfilRepository;
import com.healthsys.authservice.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final PerfilRepository perfilRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
            PerfilRepository perfilRepository,
            EspecialidadeRepository especialidadeRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.perfilRepository = perfilRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedPerfis();
        seedEspecialidades();
        seedUsuarioPadrao();
    }

    private void seedPerfis() {
        if (perfilRepository.count() == 0) {
            Perfil recepcionista = new Perfil();
            recepcionista.setDescricao("Recepcionista");
            perfilRepository.save(recepcionista);

            Perfil equipaTriagem = new Perfil();
            equipaTriagem.setDescricao("Equipe de Triagem");
            perfilRepository.save(equipaTriagem);

            Perfil enfermeiro = new Perfil();
            enfermeiro.setDescricao("Enfermeiro(a)");
            perfilRepository.save(enfermeiro);

            Perfil medico = new Perfil();
            medico.setDescricao("Médico(a)");
            perfilRepository.save(medico);

            Perfil administracao = new Perfil();
            administracao.setDescricao("Administração");
            perfilRepository.save(administracao);

            Perfil admin = new Perfil();
            admin.setDescricao("ADMIN");
            perfilRepository.save(admin);

            System.out.println("Perfis alimentados com sucesso!");
        }
    }

    private void seedEspecialidades() {
        if (especialidadeRepository.count() == 0) {
            List<String> especialidades = List.of(
                    "Clínica Geral",
                    "Cardiologia",
                    "Dermatologia",
                    "Endocrinologia",
                    "Gastroenterologia",
                    "Ginecologia e Obstetrícia",
                    "Neurologia",
                    "Oftalmologia",
                    "Ortopedia",
                    "Otorrinolaringologia",
                    "Pediatria",
                    "Psiquiatria",
                    "Urologia"
            );

            for (String descricao : especialidades) {
                Especialidade especialidade = new Especialidade();
                especialidade.setDescricao(descricao);
                especialidadeRepository.save(especialidade);
            }

            System.out.println("Especialidades alimentadas com sucesso!");
        }
    }

    private void seedUsuarioPadrao() {
        String emailPadrao = "admin@healthsys.com";

        if (usuarioRepository.existsByEmail(emailPadrao)) {
            return;
        }

        Perfil perfilAdmin = perfilRepository.findAll().stream()
                .filter(p -> "ADMIN".equalsIgnoreCase(p.getDescricao()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Perfil ADMIN não encontrado para criar usuário padrão."));

        Especialidade especialidadePadrao = especialidadeRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nenhuma especialidade encontrada para criar usuário padrão."));

        Usuario usuario = new Usuario();
        usuario.setNome("Administrador");
        usuario.setEmail(emailPadrao);
        usuario.setSenha(passwordEncoder.encode("Admin@123"));
        usuario.setDataNascimento(LocalDate.of(1990, 1, 1));
        usuario.setPerfil(perfilAdmin);
        usuario.setEspecialidade(especialidadePadrao);

        usuarioRepository.save(usuario);

        System.out.println("Usuário padrão criado com sucesso! Email: " + emailPadrao);
    }
}