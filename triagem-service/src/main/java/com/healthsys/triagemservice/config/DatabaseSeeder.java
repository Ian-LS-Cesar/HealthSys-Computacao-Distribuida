package com.healthsys.triagemservice.config;

import com.healthsys.triagemservice.model.Risco;
import com.healthsys.triagemservice.repository.RiscoRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final RiscoRepository riscoRepository;

    public DatabaseSeeder(RiscoRepository riscoRepository) {
        this.riscoRepository = riscoRepository;
    }

    @Override
    public void run(String @NonNull ... args){
        seedRiscos();
    }

    private void seedRiscos(){
        if (riscoRepository.count() == 0){
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
            vermelho.setDescricao("Vermelho - Urgente");
            riscoRepository.save(vermelho);

        }
    }
}
