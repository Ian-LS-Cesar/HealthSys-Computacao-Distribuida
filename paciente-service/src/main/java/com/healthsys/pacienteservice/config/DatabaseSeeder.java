package com.healthsys.pacienteservice.config;

import com.healthsys.pacienteservice.model.Alergia;
import com.healthsys.pacienteservice.model.Comorbidade;
import com.healthsys.pacienteservice.model.Genero;
import com.healthsys.pacienteservice.model.Sexo;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.AlergiaRepository;
import com.healthsys.pacienteservice.repository.ComorbidadeRepository;
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
    private final ComorbidadeRepository comorbidadeRepository;
    private final AlergiaRepository alergiaRepository;

    public DatabaseSeeder(
            SexoRepository sexoRepository,
            GeneroRepository generoRepository,
            VacinaRepository vacinaRepository,
            ComorbidadeRepository comorbidadeRepository,
            AlergiaRepository alergiaRepository
    ) {
        this.sexoRepository = sexoRepository;
        this.generoRepository = generoRepository;
        this.vacinaRepository = vacinaRepository;
        this.comorbidadeRepository = comorbidadeRepository;
        this.alergiaRepository = alergiaRepository;
    }

    @Override
    public void run(String @NonNull ... args) {
        seedSexos();
        seedGeneros();
        seedVacinas();
        seedComorbidades();
        seedAlergias();
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

    private void seedComorbidades() {
        if (comorbidadeRepository.count() == 0) {
            List<String> comorbidades = List.of(
                    "Hipertensão arterial sistêmica",
                    "Diabetes mellitus tipo 1",
                    "Diabetes mellitus tipo 2",
                    "Dislipidemia",
                    "Obesidade",
                    "Sobrepeso",
                    "Asma",
                    "DPOC",
                    "Bronquite crônica",
                    "Enfisema pulmonar",
                    "Insuficiência cardíaca",
                    "Arritmia cardíaca",
                    "Doença arterial coronariana",
                    "Infarto agudo do miocárdio prévio",
                    "AVC prévio",
                    "Doença renal crônica",
                    "Insuficiência renal",
                    "Doença hepática crônica",
                    "Cirrose hepática",
                    "Hepatite crônica",
                    "HIV",
                    "AIDS",
                    "Tuberculose",
                    "Hanseníase",
                    "Epilepsia",
                    "Alzheimer",
                    "Parkinson",
                    "Esclerose múltipla",
                    "Depressão",
                    "Ansiedade",
                    "Transtorno bipolar",
                    "Esquizofrenia",
                    "Autismo",
                    "TDAH",
                    "Deficiência intelectual",
                    "Câncer",
                    "Neoplasia maligna",
                    "Hipotireoidismo",
                    "Hipertireoidismo",
                    "Anemia",
                    "Anemia falciforme",
                    "Doença celíaca",
                    "Lúpus",
                    "Artrite reumatoide",
                    "Artrose",
                    "Psoríase",
                    "Gota",
                    "Osteoporose",
                    "Refluxo gastroesofágico",
                    "Gastrite crônica",
                    "Úlcera péptica",
                    "Fibromialgia",
                    "Síndrome do intestino irritável",
                    "Doença de Crohn",
                    "Retocolite ulcerativa",
                    "Miastenia gravis",
                    "Imunossupressão"
            );

            for (String descricao : comorbidades) {
                Comorbidade comorbidade = new Comorbidade();
                comorbidade.setDescricao(descricao);
                comorbidadeRepository.save(comorbidade);
            }
        }
    }

    private void seedAlergias() {
        if (alergiaRepository.count() == 0) {
            List<String> alergias = List.of(
                    "Penicilina",
                    "Amoxicilina",
                    "Dipirona",
                    "Paracetamol",
                    "Ibuprofeno",
                    "AAS (ácido acetilsalicílico)",
                    "Diclofenaco",
                    "Naproxeno",
                    "Cetoprofeno",
                    "Omeprazol",
                    "Loratadina",
                    "Benzilpenicilina",
                    "Sulfas",
                    "Cefalexina",
                    "Ceftriaxona",
                    "Anestésicos locais",
                    "Látex",
                    "Poeira",
                    "Ácaros",
                    "Pólen",
                    "Mofo",
                    "Camarão",
                    "Caranguejo",
                    "Lula",
                    "Peixes",
                    "Amendoim",
                    "Castanhas",
                    "Nozes",
                    "Leite de vaca",
                    "Ovo",
                    "Soja",
                    "Trigo",
                    "Glúten",
                    "Frutos do mar",
                    "Chocolate",
                    "Corantes alimentares",
                    "Conservantes alimentares",
                    "Perfumes",
                    "Produtos de limpeza",
                    "Pelos de animais",
                    "Picada de abelha",
                    "Picada de formiga",
                    "Picada de mosquito",
                    "Fumaça",
                    "Fragrâncias",
                    "Pimenta",
                    "Frutas cítricas",
                    "Tomate",
                    "Maracujá",
                    "Kiwi",
                    "Manga",
                    "Morango",
                    "Níquel"
            );

            for (String descricao : alergias) {
                Alergia alergia = new Alergia();
                alergia.setDescricao(descricao);
                alergiaRepository.save(alergia);
            }
        }
    }
}