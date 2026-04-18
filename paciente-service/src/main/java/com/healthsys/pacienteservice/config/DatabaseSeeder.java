package com.healthsys.pacienteservice.config;

import com.healthsys.pacienteservice.model.Sexo;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.GeneroRepository;
import com.healthsys.pacienteservice.repository.SexoRepository;
import com.healthsys.pacienteservice.repository.VacinaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final SexoRepository sexoRepository;
    private final GeneroRepository generoRepository;
    private final VacinaRepository vacinaRepository;

    public DatabaseSeeder(SexoRepository sexoRepository, GeneroRepository generoRepository, VacinaRepository vacinaRepository) {
        this.sexoRepository = sexoRepository;
        this.generoRepository = generoRepository;
        this.vacinaRepository = vacinaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedSexos();
        seedGeneros();
        seedVacinas();
    }

    private void seedSexos() {
        if (sexoRepository.count() == 0) {
            Sexo feminino = new Sexo();
            feminino.setDescricao("Feminino");
            sexoRepository.save(feminino);

            Sexo masculino = new Sexo();
            masculino.setDescricao("Masculino");
            sexoRepository.save(masculino);

            Sexo intersexo = new Sexo();
            intersexo.setDescricao("Intersexo");
            sexoRepository.save(intersexo);

            System.out.println("Sexos alimentados com sucesso!");
        }
    }

    private void seedGeneros() {
        if (generoRepository.count() == 0) {
            com.healthsys.pacienteservice.model.Genero mulherCis = new com.healthsys.pacienteservice.model.Genero();
            mulherCis.setDescricao("Mulher Cis");
            generoRepository.save(mulherCis);

            com.healthsys.pacienteservice.model.Genero homemCis = new com.healthsys.pacienteservice.model.Genero();
            homemCis.setDescricao("Homem Cis");
            generoRepository.save(homemCis);

            com.healthsys.pacienteservice.model.Genero mulherTrans = new com.healthsys.pacienteservice.model.Genero();
            mulherTrans.setDescricao("Mulher Trans");
            generoRepository.save(mulherTrans);

            com.healthsys.pacienteservice.model.Genero homemTrans = new com.healthsys.pacienteservice.model.Genero();
            homemTrans.setDescricao("Homem Trans");
            generoRepository.save(homemTrans);

            com.healthsys.pacienteservice.model.Genero travesti = new com.healthsys.pacienteservice.model.Genero();
            travesti.setDescricao("Travesti");
            generoRepository.save(travesti);

            com.healthsys.pacienteservice.model.Genero naoBinario = new com.healthsys.pacienteservice.model.Genero();
            naoBinario.setDescricao("Não-binário");
            generoRepository.save(naoBinario);

            com.healthsys.pacienteservice.model.Genero naoDeclarado = new com.healthsys.pacienteservice.model.Genero();
            naoDeclarado.setDescricao("Não declarado");
            generoRepository.save(naoDeclarado);

            com.healthsys.pacienteservice.model.Genero outro = new com.healthsys.pacienteservice.model.Genero();
            outro.setDescricao("Outro");
            generoRepository.save(outro);

            System.out.println("Gêneros alimentados com sucesso!");
        }
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

            System.out.println("Vacinas alimentadas com sucesso!");
        }
    }
}
