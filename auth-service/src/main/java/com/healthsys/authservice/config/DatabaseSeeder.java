package com.healthsys.authservice.config;

import com.healthsys.authservice.model.Perfil;
import com.healthsys.authservice.repository.PerfilRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final PerfilRepository perfilRepository;

    public DatabaseSeeder(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedPerfis();
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
}
