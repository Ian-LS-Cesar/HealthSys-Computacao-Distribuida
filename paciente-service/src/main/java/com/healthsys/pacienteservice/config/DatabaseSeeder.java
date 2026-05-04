package com.healthsys.pacienteservice.config;

import com.healthsys.pacienteservice.model.Genero;
import com.healthsys.pacienteservice.model.Sexo;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.GeneroRepository;
import com.healthsys.pacienteservice.repository.SexoRepository;
import com.healthsys.pacienteservice.repository.VacinaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final SexoRepository sexoRepository;
    private final GeneroRepository generoRepository;
    private final VacinaRepository vacinaRepository;

    public DatabaseSeeder(
            SexoRepository sexoRepository,
            GeneroRepository generoRepository,
            VacinaRepository vacinaRepository
    ) {
        this.sexoRepository = sexoRepository;
        this.generoRepository = generoRepository;
        this.vacinaRepository = vacinaRepository;
    }

    @Override
    public void run(String @NonNull ... args) {
        seedSexos();
        seedGeneros();
        seedVacinas();
    }

    private void seedSexos() {
        if (sexoRepository.count() == 0) {
            sexoRepository.save(criarSexo("Feminino"));
            sexoRepository.save(criarSexo("Masculino"));
            sexoRepository.save(criarSexo("Intersexo"));
        }
    }

    private Sexo criarSexo(String descricao) {
        Sexo sexo = new Sexo();
        sexo.setDescricao(descricao);
        return sexo;
    }

    private void seedGeneros() {
        if (generoRepository.count() == 0) {
            generoRepository.save(criarGenero("Mulher Cis"));
            generoRepository.save(criarGenero("Homem Cis"));
            generoRepository.save(criarGenero("Mulher Trans"));
            generoRepository.save(criarGenero("Homem Trans"));
            generoRepository.save(criarGenero("Travesti"));
            generoRepository.save(criarGenero("Não-binário"));
            generoRepository.save(criarGenero("Não declarado"));
            generoRepository.save(criarGenero("Outro"));
        }
    }

    private Genero criarGenero(String descricao) {
        Genero genero = new Genero();
        genero.setDescricao(descricao);
        return genero;
    }

    private void seedVacinas() {
        if (vacinaRepository.count() == 0) {
            List<String> vacinas = List.of(
                    "BCG",
                    "Hepatite B",
                    "Pentavalente",
                    "Poliomielite (VIP/VOP)",
                    "Rotavírus",
                    "Pneumocócica 10",
                    "Meningocócica C",
                    "Febre Amarela",
                    "Tríplice Viral (SCR)",
                    "Tetraviral",
                    "DTP",
                    "Hepatite A",
                    "HPV",
                    "dT (Dupla Adulto)",
                    "Influenza",
                    "COVID-19"
            );

            for (String nomeVacina : vacinas) {
                Vacina vacina = new Vacina();
                vacina.setNome(nomeVacina);
                vacinaRepository.save(vacina);
            }
        }
    }
}