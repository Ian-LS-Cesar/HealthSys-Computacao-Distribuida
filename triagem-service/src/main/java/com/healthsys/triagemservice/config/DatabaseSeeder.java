package com.healthsys.triagemservice.config;

import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.model.Sintoma;
import com.healthsys.triagemservice.repository.RiscoRepository;
import com.healthsys.triagemservice.repository.SintomaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final RiscoRepository riscoRepository;
    private final SintomaRepository sintomaRepository;

    public DatabaseSeeder(RiscoRepository riscoRepository, SintomaRepository sintomaRepository) {
        this.riscoRepository = riscoRepository;
        this.sintomaRepository = sintomaRepository;
    }

    @Override
    public void run(String @NonNull ... args) {
        seedRiscos();
        seedSintomas();
    }

    private void seedRiscos() {
        if (riscoRepository.count() == 0) {
            Risco azul = new Risco();
            azul.setDescricao("Azul - Não Urgente");
            riscoRepository.save(azul);

            Risco verde = new Risco();
            verde.setDescricao("Verde - Pouco Urgente");
            riscoRepository.save(verde);

            Risco amarelo = new Risco();
            amarelo.setDescricao("Amarelo - Urgente");
            riscoRepository.save(amarelo);

            Risco vermelho = new Risco();
            vermelho.setDescricao("Vermelho - Muito Urgente");
            riscoRepository.save(vermelho);
        }
    }

    private void seedSintomas() {
        if (sintomaRepository.count() > 0) {
            return;
        }

        Risco risco1 = riscoRepository.findById(1).orElseThrow();
        Risco risco2 = riscoRepository.findById(2).orElseThrow();
        Risco risco3 = riscoRepository.findById(3).orElseThrow();
        Risco risco4 = riscoRepository.findById(4).orElseThrow();

        criarSintoma("Parada cardiorrespiratória", risco4);
        criarSintoma("Obstrução de vias aéreas", risco4);
        criarSintoma("Hemorragia massiva não controlada", risco4);
        criarSintoma("Inconsciência ou rebaixamento de consciência", risco4);
        criarSintoma("Convulsão ativa", risco4);
        criarSintoma("Dor no peito intensa (Suspeita de IAM)", risco4);
        criarSintoma("Trauma cranioencefálico grave", risco4);
        criarSintoma("Dispneia grave (Falta de ar extrema)", risco4);
        criarSintoma("Grande queimadura", risco4);
        criarSintoma("Choque anafilático", risco4);

        criarSintoma("Dor moderada a intensa", risco3);
        criarSintoma("Febre alta (Acima de 39°C)", risco3);
        criarSintoma("Vômitos persistentes", risco3);
        criarSintoma("Desmaio (Síncope) com recuperação", risco3);
        criarSintoma("Alteração súbita de fala ou visão", risco3);
        criarSintoma("Fraturas fechadas", risco3);
        criarSintoma("Hemorragia moderada controlada", risco3);
        criarSintoma("Crise hipertensiva sintomática", risco3);
        criarSintoma("Crise asmática moderada", risco3);

        criarSintoma("Dor leve", risco2);
        criarSintoma("Sintomas gripais sem falta de ar", risco2);
        criarSintoma("Diarreia sem desidratação", risco2);
        criarSintoma("Entorses e pequenas contusões", risco2);
        criarSintoma("Cefaleia leve", risco2);
        criarSintoma("Erupções cutâneas simples", risco2);
        criarSintoma("Vômito isolado", risco2);

        criarSintoma("Dores crônicas (Longa duração)", risco1);
        criarSintoma("Troca de curativos", risco1);
        criarSintoma("Retirada de pontos", risco1);
        criarSintoma("Solicitação de receitas", risco1);
        criarSintoma("Avaliação de exames laboratoriais", risco1);
        criarSintoma("Pequenas escoriações e arranhões", risco1);
        criarSintoma("Encaminhamentos médicos", risco1);
    }

    private void criarSintoma(String descricao, Risco risco) {
        Sintoma sintoma = new Sintoma();
        sintoma.setDescricao(descricao);
        sintoma.setRisco(risco);
        sintomaRepository.save(sintoma);
    }
}