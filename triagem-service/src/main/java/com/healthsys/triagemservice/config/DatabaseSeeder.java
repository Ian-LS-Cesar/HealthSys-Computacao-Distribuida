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

            Risco laranja = new Risco();
            laranja.setDescricao("Laranja - Muito Urgente");
            riscoRepository.save(laranja);

            Risco vermelho = new Risco();
            vermelho.setDescricao("Vermelho - Emergência");
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
        Risco risco5 = riscoRepository.findById(5).orElseThrow();

        // Nível 5
        criarSintoma("Parada cardiorrespiratória", risco5);
        criarSintoma("Obstrução de vias aéreas", risco5);
        criarSintoma("Obstrução total de vias aéreas (Asfixia)", risco5);
        criarSintoma("Hemorragia massiva não controlada", risco5);
        criarSintoma("Inconsciência ou rebaixamento de consciência", risco5);
        criarSintoma("Inconsciência ou Coma", risco5);
        criarSintoma("Convulsão ativa", risco5);
        criarSintoma("Choque (Sinais de má perfusão)", risco5);
        criarSintoma("Choque anafilático", risco5);
        criarSintoma("Trauma craniano grave com perda de consciência", risco5);
        criarSintoma("Trauma cranioencefálico grave", risco5);
        criarSintoma("Ferimento penetrante em tórax ou abdome", risco5);
        criarSintoma("Insuficiência respiratória grave", risco5);
        criarSintoma("Dispneia grave (Falta de ar extrema)", risco5);
        criarSintoma("Queimaduras de vias aéreas", risco5);
        criarSintoma("Grande queimadura", risco5);

        // Nível 4
        criarSintoma("Dor precordial intensa (Suspeita de Infarto)", risco4);
        criarSintoma("Déficit neurológico súbito (Suspeita de AVC)", risco4);
        criarSintoma("Crise asmática grave (Uso de musculatura acessória)", risco4);
        criarSintoma("Grandes queimaduras (>20% de superfície corporal)", risco4);
        criarSintoma("Cetoacidose diabética (Alteração de consciência)", risco4);
        criarSintoma("Febre em recém-nascidos (Menores de 3 meses)", risco4);
        criarSintoma("Intoxicação exógena aguda", risco4);
        criarSintoma("Dor intensa súbita (Escala 8 a 10)", risco4);
        criarSintoma("Dor no peito intensa (Suspeita de IAM)", risco4);
        criarSintoma("Arritmias cardíacas instáveis", risco4);
        criarSintoma("Fraturas expostas", risco4);

        // Nível 3
        criarSintoma("Dor moderada aguda (Escala 4 a 7)", risco3);
        criarSintoma("Vômitos persistentes", risco3);
        criarSintoma("Vômitos e diarreia persistentes", risco3);
        criarSintoma("Febre alta (Acima de 39°C)", risco3);
        criarSintoma("Febre persistente (Acima de 38.5°C)", risco3);
        criarSintoma("Desmaio (Síncope) com recuperação", risco3);
        criarSintoma("Síncope (Desmaio) com recuperação total", risco3);
        criarSintoma("Alteração súbita de fala ou visão", risco3);
        criarSintoma("Crise de ansiedade grave", risco3);
        criarSintoma("Cólicas renais ou biliares", risco3);
        criarSintoma("Hemorragia moderada controlada", risco3);
        criarSintoma("Hipertensão arterial sintomática", risco3);
        criarSintoma("Crise hipertensiva sintomática", risco3);
        criarSintoma("Fraturas fechadas", risco3);
        criarSintoma("Fraturas fechadas sem deformidade grave", risco3);
        criarSintoma("Paciente com mobilidade reduzida aguda", risco3);
        criarSintoma("Crise asmática moderada", risco3);

        // Nível 2
        criarSintoma("Dor leve", risco2);
        criarSintoma("Sintomas gripais sem falta de ar", risco2);
        criarSintoma("Sintomas gripais estáveis (Sem falta de ar)", risco2);
        criarSintoma("Diarreia sem desidratação", risco2);
        criarSintoma("Vômito isolado", risco2);
        criarSintoma("Vômito isolado (Sem sinais de desidratação)", risco2);
        criarSintoma("Entorses e pequenas contusões", risco2);
        criarSintoma("Cefaleia leve", risco2);
        criarSintoma("Cefaleia (Dor de cabeça) leve e comum", risco2);
        criarSintoma("Erupções cutâneas simples", risco2);
        criarSintoma("Reação alérgica leve (Urticária localizada)", risco2);
        criarSintoma("Lombalgia (Dor nas costas) leve ou moderada", risco2);
        criarSintoma("Abscessos sem febre", risco2);
        criarSintoma("Conjuntivite ou irritação ocular", risco2);
        criarSintoma("Disúria (Ardor ao urinar) sem febre", risco2);

        // Nível 1
        criarSintoma("Dores crônicas (Longa duração)", risco1);
        criarSintoma("Dores crônicas (Duração de meses ou anos)", risco1);
        criarSintoma("Troca de curativos", risco1);
        criarSintoma("Troca de curativos simples", risco1);
        criarSintoma("Retirada de pontos", risco1);
        criarSintoma("Solicitação de receitas", risco1);
        criarSintoma("Renovação de receitas", risco1);
        criarSintoma("Avaliação de exames laboratoriais", risco1);
        criarSintoma("Pequenas escoriações e arranhões", risco1);
        criarSintoma("Pequenas escoriações ou arranhões", risco1);
        criarSintoma("Encaminhamentos médicos", risco1);
        criarSintoma("Encaminhamentos para especialistas", risco1);
        criarSintoma("Solicitação de atestados ou laudos", risco1);
        criarSintoma("Avaliação de lesões de pele antigas", risco1);
        criarSintoma("Orientações médicas gerais", risco1);
    }

    private void criarSintoma(String descricao, Risco risco) {
        Sintoma sintoma = new Sintoma();
        sintoma.setDescricao(descricao);
        sintoma.setRisco(risco);
        sintomaRepository.save(sintoma);
    }
}