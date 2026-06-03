package com.healthsys.pacienteservice.config;

import com.healthsys.pacienteservice.model.Alergia;
import com.healthsys.pacienteservice.model.Comorbidade;
import com.healthsys.pacienteservice.model.Endereco;
import com.healthsys.pacienteservice.model.Genero;
import com.healthsys.pacienteservice.model.Paciente;
import com.healthsys.pacienteservice.model.Sexo;
import com.healthsys.pacienteservice.model.Telefone;
import com.healthsys.pacienteservice.model.Vacina;
import com.healthsys.pacienteservice.repository.AlergiaRepository;
import com.healthsys.pacienteservice.repository.ComorbidadeRepository;
import com.healthsys.pacienteservice.repository.GeneroRepository;
import com.healthsys.pacienteservice.repository.PacienteRepository;
import com.healthsys.pacienteservice.repository.SexoRepository;
import com.healthsys.pacienteservice.repository.VacinaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final SexoRepository sexoRepository;
    private final GeneroRepository generoRepository;
    private final VacinaRepository vacinaRepository;
    private final ComorbidadeRepository comorbidadeRepository;
    private final AlergiaRepository alergiaRepository;
    private final PacienteRepository pacienteRepository;

    public DatabaseSeeder(
            SexoRepository sexoRepository,
            GeneroRepository generoRepository,
            VacinaRepository vacinaRepository,
            ComorbidadeRepository comorbidadeRepository,
            AlergiaRepository alergiaRepository,
            PacienteRepository pacienteRepository
    ) {
        this.sexoRepository = sexoRepository;
        this.generoRepository = generoRepository;
        this.vacinaRepository = vacinaRepository;
        this.comorbidadeRepository = comorbidadeRepository;
        this.alergiaRepository = alergiaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void run(String @NonNull ... args) {
        seedSexos();
        seedGeneros();
        seedVacinas();
        seedComorbidades();
        seedAlergias();
        // seedPacientes DEVE ser o último a rodar, pois depende de todas as tabelas acima
        seedPacientes();
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
                    "BCG", "Hepatite B", "Pentavalente", "Poliomielite (VIP/VOP)",
                    "Rotavírus", "Pneumocócica 10", "Meningocócica C", "Febre Amarela",
                    "Tríplice Viral (SCR)", "Tetraviral", "DTP", "Hepatite A",
                    "HPV", "dT (Dupla Adulto)", "Influenza", "COVID-19"
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
                    "Hipertensão arterial sistêmica", "Diabetes mellitus tipo 1", "Diabetes mellitus tipo 2",
                    "Dislipidemia", "Obesidade", "Sobrepeso", "Asma", "DPOC", "Bronquite crônica",
                    "Enfisema pulmonar", "Insuficiência cardíaca", "Arritmia cardíaca", "Doença arterial coronariana",
                    "Infarto agudo do miocárdio prévio", "AVC prévio", "Doença renal crônica", "Insuficiência renal",
                    "Doença hepática crônica", "Cirrose hepática", "Hepatite crônica", "HIV", "AIDS", "Tuberculose",
                    "Hanseníase", "Epilepsia", "Alzheimer", "Parkinson", "Esclerose múltipla", "Depressão",
                    "Ansiedade", "Transtorno bipolar", "Esquizofrenia", "Autismo", "TDAH", "Deficiência intelectual",
                    "Câncer", "Neoplasia maligna", "Hipotireoidismo", "Hipertireoidismo", "Anemia", "Anemia falciforme",
                    "Doença celíaca", "Lúpus", "Artrite reumatoide", "Artrose", "Psoríase", "Gota", "Osteoporose",
                    "Refluxo gastroesofágico", "Gastrite crônica", "Úlcera péptica", "Fibromialgia",
                    "Síndrome do intestino irritável", "Doença de Crohn", "Retocolite ulcerativa", "Miastenia gravis",
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
                    "Penicilina", "Amoxicilina", "Dipirona", "Paracetamol", "Ibuprofeno",
                    "AAS (ácido acetilsalicílico)", "Diclofenaco", "Naproxeno", "Cetoprofeno", "Omeprazol",
                    "Loratadina", "Benzilpenicilina", "Sulfas", "Cefalexina", "Ceftriaxona",
                    "Anestésicos locais", "Látex", "Poeira", "Ácaros", "Pólen", "Mofo", "Camarão",
                    "Caranguejo", "Lula", "Peixes", "Amendoim", "Castanhas", "Nozes", "Leite de vaca",
                    "Ovo", "Soja", "Trigo", "Glúten", "Frutos do mar", "Chocolate", "Corantes alimentares",
                    "Conservantes alimentares", "Perfumes", "Produtos de limpeza", "Pelos de animais",
                    "Picada de abelha", "Picada de formiga", "Picada de mosquito", "Fumaça", "Fragrâncias",
                    "Pimenta", "Frutas cítricas", "Tomate", "Maracujá", "Kiwi", "Manga", "Morango", "Níquel"
            );

            for (String descricao : alergias) {
                Alergia alergia = new Alergia();
                alergia.setDescricao(descricao);
                alergiaRepository.save(alergia);
            }
        }
    }

    private void seedPacientes() {
        if (pacienteRepository.count() == 0) {
            List<Paciente> pacientes = List.of(
                    // 1 - Mulher Cis (Sem nome social)
                    criarPaciente("Ana Beatriz Silva", null, "1990-05-14", 1, 1, "12345678901",
                            "85999999991", "Rua A", "10", "Centro", "Fortaleza", "CE", "60000000",
                            List.of(1, 2), List.of(4)), // Hipertensão(1), Diabetes 1(2) | Paracetamol(4)

                    // 2 - Homem Cis (Sem nome social)
                    criarPaciente("Carlos Eduardo Mendes", null, "1982-10-21", 2, 2, "23456789012",
                            "11988888882", "Av Paulista", "1000", "Bela Vista", "São Paulo", "SP", "01310100",
                            List.of(5), List.of(1, 2)), // Obesidade(5) | Penicilina(1), Amoxicilina(2)

                    // 3 - Mulher Trans (Com nome social)
                    criarPaciente("Roberto Fernandes", "Roberta Fernandes", "1995-02-10", 3, 2, "34567890123",
                            "21977777773", "Rua Copacabana", "500", "Copacabana", "Rio de Janeiro", "RJ", "22020000",
                            List.of(30), List.of(17)), // Ansiedade(30) | Látex(17)

                    // 4 - Homem Trans (Com nome social)
                    criarPaciente("Mariana Costa", "Mário Costa", "1998-08-30", 4, 1, "45678901234",
                            "31966666664", "Rua da Bahia", "150", "Lourdes", "Belo Horizonte", "MG", "30160010",
                            List.of(7), List.of()), // Asma(7) | Nenhuma alergia

                    // 5 - Não-binário (Com nome social)
                    criarPaciente("Fernanda Lima", "Fê Lima", "2000-11-05", 6, 1, "56789012345",
                            "81955555555", "Av Boa Viagem", "200", "Boa Viagem", "Recife", "PE", "51011000",
                            List.of(), List.of(34, 35)), // Nenhuma comorbidade | Frutos do mar(34), Chocolate(35)

                    // 6 - Travesti (Com nome social)
                    criarPaciente("Paulo Souza", "Paula Souza", "1988-07-19", 5, 2, "67890123456",
                            "41944444446", "Rua XV de Novembro", "300", "Centro", "Curitiba", "PR", "80020310",
                            List.of(21), List.of(18, 19)), // HIV(21) | Poeira(18), Ácaros(19)

                    // 7 - Mulher Cis (Sem nome social)
                    criarPaciente("Juliana Martins", null, "1975-01-25", 1, 1, "78901234567",
                            "62933333337", "Av Goiás", "400", "Setor Central", "Goiânia", "GO", "74005010",
                            List.of(16), List.of(13)), // Doença renal crônica(16) | Sulfas(13)

                    // 8 - Homem Cis (Sem nome social)
                    criarPaciente("Ricardo Almeida", null, "1960-12-12", 2, 2, "89012345678",
                            "71922222228", "Av Oceânica", "600", "Ondina", "Salvador", "BA", "40170010",
                            List.of(13, 14), List.of()), // Doença arterial(13), Infarto(14) | Nenhuma alergia

                    // 9 - Outro (Com nome social)
                    criarPaciente("Camila Pereira", "Cami Pereira", "1993-04-18", 8, 1, "90123456789",
                            "51911111119", "Rua dos Andradas", "800", "Centro Histórico", "Porto Alegre", "RS", "90020004",
                            List.of(29, 34), List.of(26, 27)), // Depressão(29), TDAH(34) | Amendoim(26), Castanhas(27)

                    // 10 - Mulher Cis (Sem nome social)
                    criarPaciente("Letícia Rocha", null, "2005-09-07", 1, 1, "98765432109",
                            "98900000000", "Av Litorânea", "900", "Calhau", "São Luís", "MA", "65076170",
                            List.of(), List.of(41)) // Nenhuma comorbidade | Picada de abelha(41)
            );

            pacienteRepository.saveAll(pacientes);
        }
    }

    private Paciente criarPaciente(String nome, String nomeSocial, String dataNascimento,
                                   Integer generoId, Integer sexoId, String cpf,
                                   String telefone, String logradouro, String numero,
                                   String bairro, String cidade, String uf, String cep,
                                   List<Integer> comorbidadesIds, List<Integer> alergiasIds) {

        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setNomeSocial(nomeSocial);
        paciente.setDataNascimento(LocalDate.parse(dataNascimento));
        paciente.setCpf(cpf);

        // Busca e atribui os relacionamentos unitários (Apenas Integers)
        generoRepository.findById(generoId).ifPresent(paciente::setGenero);
        sexoRepository.findById(sexoId).ifPresent(paciente::setSexo);

        // Instancia e vincula telefones (embrulhado em ArrayList para garantir mutabilidade caso sua model dependa disso)
        Telefone tel = new Telefone();
        tel.setNumero(telefone);
        tel.setPaciente(paciente);
        paciente.setTelefones(new ArrayList<>(List.of(tel)));

        // Instancia e vincula endereços
        Endereco end = new Endereco();
        end.setLogradouro(logradouro);
        end.setNumero(numero);
        end.setBairro(bairro);
        end.setCidade(cidade);
        end.setUf(uf);
        end.setCep(cep);
        end.setPaciente(paciente);
        paciente.setEnderecos(new ArrayList<>(List.of(end)));

        // Popula as tabelas associativas
        List<Comorbidade> comorbidades = new ArrayList<>();
        for (Integer id : comorbidadesIds) {
            comorbidadeRepository.findById(id).ifPresent(comorbidades::add);
        }
        paciente.setComorbidades(comorbidades);

        List<Alergia> alergias = new ArrayList<>();
        for (Integer id : alergiasIds) {
            alergiaRepository.findById(id).ifPresent(alergias::add);
        }
        paciente.setAlergias(alergias);

        return paciente;
    }
}