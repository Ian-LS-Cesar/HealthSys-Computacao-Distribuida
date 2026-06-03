-- ==========================================
-- 1. POPULANDO TABELAS DOMÍNIO
-- ==========================================

-- Sexos (INT)
INSERT INTO sexo (id, descricao) VALUES
                                     (1, 'Feminino'),
                                     (2, 'Masculino'),
                                     (3, 'Intersexo')
    ON CONFLICT (id) DO NOTHING;

-- Gêneros (INT)
INSERT INTO genero (id, descricao) VALUES
                                       (1, 'Mulher Cis'), (2, 'Homem Cis'), (3, 'Mulher Trans'),
                                       (4, 'Homem Trans'), (5, 'Travesti'), (6, 'Não-binário'),
                                       (7, 'Não declarado'), (8, 'Outro')
    ON CONFLICT (id) DO NOTHING;

-- Vacinas (UUID)
INSERT INTO vacina (id, nome) VALUES
                                  ('14e9a86b-f7f8-43f0-b66a-e9cfa4955098', 'BCG'),
                                  ('fd836228-c161-43da-9f2a-4429744e5abb', 'Hepatite B'),
                                  ('b4f19d57-1dba-4c8e-949d-9aced2c540d1', 'Pentavalente'),
                                  ('705ab6bc-489c-4700-b8e8-90c8b838e381', 'Poliomielite (VIP/VOP)'),
                                  ('888f9a1b-99bc-4959-aa85-7af82af92312', 'Rotavírus'),
                                  ('e02ce23f-974b-44c6-af90-048ab9bf6cf3', 'Pneumocócica 10'),
                                  ('7138a515-592f-47fe-8cb7-6dd052ee0da2', 'Meningocócica C'),
                                  ('539ddac4-fbb3-4bee-903b-633f5762cf07', 'Febre Amarela'),
                                  ('00358b2b-d2f9-4344-83a9-022daead982b', 'Tríplice Viral (SCR)'),
                                  ('6e665277-9b8d-4041-89f4-039f1692c906', 'Tetraviral'),
                                  ('bbbe1004-4ca6-4fb3-a3a3-e05a02f1b590', 'DTP'),
                                  ('180036f0-2cf9-4546-b5b8-ea4262d43843', 'Hepatite A'),
                                  ('167aeb0d-80ff-49c9-9ce3-2658748ed03d', 'HPV'),
                                  ('c5f13b91-f4ca-42d9-8d23-32859d68873b', 'dT (Dupla Adulto)'),
                                  ('0b96ed70-9983-4d55-bf70-36a4f753a494', 'Influenza'),
                                  ('2aaf52ad-3438-4bcf-9638-69f4eda156ae', 'COVID-19')
    ON CONFLICT (id) DO NOTHING;

-- Comorbidades (INT)
INSERT INTO comorbidade (id, descricao) VALUES
                                            (1, 'Hipertensão arterial sistêmica'), (2, 'Diabetes mellitus tipo 1'), (3, 'Diabetes mellitus tipo 2'),
                                            (4, 'Dislipidemia'), (5, 'Obesidade'), (6, 'Sobrepeso'), (7, 'Asma'), (8, 'DPOC'), (9, 'Bronquite crônica'),
                                            (10, 'Enfisema pulmonar'), (11, 'Insuficiência cardíaca'), (12, 'Arritmia cardíaca'), (13, 'Doença arterial coronariana'),
                                            (14, 'Infarto agudo do miocárdio prévio'), (15, 'AVC prévio'), (16, 'Doença renal crônica'), (17, 'Insuficiência renal'),
                                            (18, 'Doença hepática crônica'), (19, 'Cirrose hepática'), (20, 'Hepatite crônica'), (21, 'HIV'), (22, 'AIDS'), (23, 'Tuberculose'),
                                            (24, 'Hanseníase'), (25, 'Epilepsia'), (26, 'Alzheimer'), (27, 'Parkinson'), (28, 'Esclerose múltipla'), (29, 'Depressão'),
                                            (30, 'Ansiedade'), (31, 'Transtorno bipolar'), (32, 'Esquizofrenia'), (33, 'Autismo'), (34, 'TDAH'), (35, 'Deficiência intelectual'),
                                            (36, 'Câncer'), (37, 'Neoplasia maligna'), (38, 'Hipotireoidismo'), (39, 'Hipertireoidismo'), (40, 'Anemia'), (41, 'Anemia falciforme'),
                                            (42, 'Doença celíaca'), (43, 'Lúpus'), (44, 'Artrite reumatoide'), (45, 'Artrose'), (46, 'Psoríase'), (47, 'Gota'), (48, 'Osteoporose'),
                                            (49, 'Refluxo gastroesofágico'), (50, 'Gastrite crônica'), (51, 'Úlcera péptica'), (52, 'Fibromialgia'),
                                            (53, 'Síndrome do intestino irritável'), (54, 'Doença de Crohn'), (55, 'Retocolite ulcerativa'), (56, 'Miastenia gravis'),
                                            (57, 'Imunossupressão')
    ON CONFLICT (id) DO NOTHING;

-- Alergias (INT)
INSERT INTO alergia (id, descricao) VALUES
                                        (1, 'Penicilina'), (2, 'Amoxicilina'), (3, 'Dipirona'), (4, 'Paracetamol'), (5, 'Ibuprofeno'),
                                        (6, 'AAS (ácido acetilsalicílico)'), (7, 'Diclofenaco'), (8, 'Naproxeno'), (9, 'Cetoprofeno'), (10, 'Omeprazol'),
                                        (11, 'Loratadina'), (12, 'Benzilpenicilina'), (13, 'Sulfas'), (14, 'Cefalexina'), (15, 'Ceftriaxona'),
                                        (16, 'Anestésicos locais'), (17, 'Látex'), (18, 'Poeira'), (19, 'Ácaros'), (20, 'Pólen'), (21, 'Mofo'), (22, 'Camarão'),
                                        (23, 'Caranguejo'), (24, 'Lula'), (25, 'Peixes'), (26, 'Amendoim'), (27, 'Castanhas'), (28, 'Nozes'), (29, 'Leite de vaca'),
                                        (30, 'Ovo'), (31, 'Soja'), (32, 'Trigo'), (33, 'Glúten'), (34, 'Frutos do mar'), (35, 'Chocolate'), (36, 'Corantes alimentares'),
                                        (37, 'Conservantes alimentares'), (38, 'Perfumes'), (39, 'Produtos de limpeza'), (40, 'Pelos de animais'),
                                        (41, 'Picada de abelha'), (42, 'Picada de formiga'), (43, 'Picada de mosquito'), (44, 'Fumaça'), (45, 'Fragrâncias'),
                                        (46, 'Pimenta'), (47, 'Frutas cítricas'), (48, 'Tomate'), (49, 'Maracujá'), (50, 'Kiwi'), (51, 'Manga'), (52, 'Morango'), (53, 'Níquel')
    ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 2. POPULANDO PACIENTES
-- ==========================================
-- Nomes das colunas ajustados para "genero" e "sexo" conforme o @JoinColumn. IDs em UUID.
INSERT INTO paciente (id, nome, nome_social, data_nascimento, genero, sexo, cpf) VALUES
                                                                                     ('11111111-1111-1111-1111-111111111111', 'Ana Beatriz Silva', NULL, '1990-05-14', 1, 1, '12345678901'),
                                                                                     ('22222222-2222-2222-2222-222222222222', 'Carlos Eduardo Mendes', NULL, '1982-10-21', 2, 2, '23456789012'),
                                                                                     ('33333333-3333-3333-3333-333333333333', 'Roberto Fernandes', 'Roberta Fernandes', '1995-02-10', 3, 2, '34567890123'),
                                                                                     ('44444444-4444-4444-4444-444444444444', 'Mariana Costa', 'Mário Costa', '1998-08-30', 4, 1, '45678901234'),
                                                                                     ('55555555-5555-5555-5555-555555555555', 'Fernanda Lima', 'Fê Lima', '2000-11-05', 6, 1, '56789012345'),
                                                                                     ('66666666-6666-6666-6666-666666666666', 'Paulo Souza', 'Paula Souza', '1988-07-19', 5, 2, '67890123456'),
                                                                                     ('77777777-7777-7777-7777-777777777777', 'Juliana Martins', NULL, '1975-01-25', 1, 1, '78901234567'),
                                                                                     ('88888888-8888-8888-8888-888888888888', 'Ricardo Almeida', NULL, '1960-12-12', 2, 2, '89012345678'),
                                                                                     ('99999999-9999-9999-9999-999999999999', 'Camila Pereira', 'Cami Pereira', '1993-04-18', 8, 1, '90123456789'),
                                                                                     ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Letícia Rocha', NULL, '2005-09-07', 1, 1, '98765432109')
    ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 3. POPULANDO ENDEREÇOS E TELEFONES
-- ==========================================
-- Voltando para INT (conforme os models Endereco e Telefone), mantendo o paciente_id como UUID.
INSERT INTO endereco (id, logradouro, numero, bairro, cidade, uf, cep, paciente_id) VALUES
                                                                                        (1, 'Rua A', '10', 'Centro', 'Fortaleza', 'CE', '60000000', '11111111-1111-1111-1111-111111111111'),
                                                                                        (2, 'Av Paulista', '1000', 'Bela Vista', 'São Paulo', 'SP', '01310100', '22222222-2222-2222-2222-222222222222'),
                                                                                        (3, 'Rua Copacabana', '500', 'Copacabana', 'Rio de Janeiro', 'RJ', '22020000', '33333333-3333-3333-3333-333333333333'),
                                                                                        (4, 'Rua da Bahia', '150', 'Lourdes', 'Belo Horizonte', 'MG', '30160010', '44444444-4444-4444-4444-444444444444'),
                                                                                        (5, 'Av Boa Viagem', '200', 'Boa Viagem', 'Recife', 'PE', '51011000', '55555555-5555-5555-5555-555555555555'),
                                                                                        (6, 'Rua XV de Novembro', '300', 'Centro', 'Curitiba', 'PR', '80020310', '66666666-6666-6666-6666-666666666666'),
                                                                                        (7, 'Av Goiás', '400', 'Setor Central', 'Goiânia', 'GO', '74005010', '77777777-7777-7777-7777-777777777777'),
                                                                                        (8, 'Av Oceânica', '600', 'Ondina', 'Salvador', 'BA', '40170010', '88888888-8888-8888-8888-888888888888'),
                                                                                        (9, 'Rua dos Andradas', '800', 'Centro Histórico', 'Porto Alegre', 'RS', '90020004', '99999999-9999-9999-9999-999999999999'),
                                                                                        (10, 'Av Litorânea', '900', 'Calhau', 'São Luís', 'MA', '65076170', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa')
    ON CONFLICT (id) DO NOTHING;

INSERT INTO telefone (id, numero, paciente_id) VALUES
                                                   (1, '85999999991', '11111111-1111-1111-1111-111111111111'),
                                                   (2, '11988888882', '22222222-2222-2222-2222-222222222222'),
                                                   (3, '21977777773', '33333333-3333-3333-3333-333333333333'),
                                                   (4, '31966666664', '44444444-4444-4444-4444-444444444444'),
                                                   (5, '81955555555', '55555555-5555-5555-5555-555555555555'),
                                                   (6, '41944444446', '66666666-6666-6666-6666-666666666666'),
                                                   (7, '62933333337', '77777777-7777-7777-7777-777777777777'),
                                                   (8, '71922222228', '88888888-8888-8888-8888-888888888888'),
                                                   (9, '51911111119', '99999999-9999-9999-9999-999999999999'),
                                                   (10, '98900000000', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa')
    ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 4. TABELAS ASSOCIATIVAS (Many-To-Many)
-- ==========================================
-- Removi o ON CONFLICT pois tabelas associativas simples geralmente não recebem 'id' primário explícito da JPA para conflitos genéricos
INSERT INTO paciente_comorbidade (paciente_id, comorbidade_id) VALUES
                                                                   ('11111111-1111-1111-1111-111111111111', 1),
                                                                   ('11111111-1111-1111-1111-111111111111', 2),
                                                                   ('22222222-2222-2222-2222-222222222222', 5),
                                                                   ('33333333-3333-3333-3333-333333333333', 30),
                                                                   ('44444444-4444-4444-4444-444444444444', 7),
                                                                   ('66666666-6666-6666-6666-666666666666', 21),
                                                                   ('77777777-7777-7777-7777-777777777777', 16),
                                                                   ('88888888-8888-8888-8888-888888888888', 13),
                                                                   ('88888888-8888-8888-8888-888888888888', 14),
                                                                   ('99999999-9999-9999-9999-999999999999', 29),
                                                                   ('99999999-9999-9999-9999-999999999999', 34);

INSERT INTO paciente_alergia (paciente_id, alergia_id) VALUES
                                                           ('11111111-1111-1111-1111-111111111111', 4),
                                                           ('22222222-2222-2222-2222-222222222222', 1),
                                                           ('22222222-2222-2222-2222-222222222222', 2),
                                                           ('33333333-3333-3333-3333-333333333333', 17),
                                                           ('55555555-5555-5555-5555-555555555555', 34),
                                                           ('55555555-5555-5555-5555-555555555555', 35),
                                                           ('66666666-6666-6666-6666-666666666666', 18),
                                                           ('66666666-6666-6666-6666-666666666666', 19),
                                                           ('77777777-7777-7777-7777-777777777777', 13),
                                                           ('99999999-9999-9999-9999-999999999999', 26),
                                                           ('99999999-9999-9999-9999-999999999999', 27),
                                                           ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 41);

-- ==========================================
-- 5. POPULANDO HISTÓRICO DE VACINAS (PacienteVacina)
-- ==========================================

INSERT INTO paciente_vacina (id, paciente_id, vacina_id, data_aplicacao) VALUES
-- Paciente 1 (Ana Beatriz): BCG e 2 doses de COVID-19 em datas diferentes
('d0000000-0000-0000-0000-000000000001', '11111111-1111-1111-1111-111111111111', '14e9a86b-f7f8-43f0-b66a-e9cfa4955098', '1990-05-20'),
('d0000000-0000-0000-0000-000000000002', '11111111-1111-1111-1111-111111111111', '2aaf52ad-3438-4bcf-9638-69f4eda156ae', '2021-06-15'),
('d0000000-0000-0000-0000-000000000003', '11111111-1111-1111-1111-111111111111', '2aaf52ad-3438-4bcf-9638-69f4eda156ae', '2021-09-15'),

-- Paciente 2 (Carlos Eduardo): Hepatite B e Febre Amarela
('d0000000-0000-0000-0000-000000000004', '22222222-2222-2222-2222-222222222222', 'fd836228-c161-43da-9f2a-4429744e5abb', '1982-11-01'),
('d0000000-0000-0000-0000-000000000005', '22222222-2222-2222-2222-222222222222', '539ddac4-fbb3-4bee-903b-633f5762cf07', '2015-02-10'),

-- Paciente 3 (Roberto/Roberta): Influenza
('d0000000-0000-0000-0000-000000000006', '33333333-3333-3333-3333-333333333333', '0b96ed70-9983-4d55-bf70-36a4f753a494', '2023-04-20'),

-- Paciente 4 (Mariana/Mário): Pentavalente, Rotavírus e Poliomielite
('d0000000-0000-0000-0000-000000000007', '44444444-4444-4444-4444-444444444444', 'b4f19d57-1dba-4c8e-949d-9aced2c540d1', '1998-10-30'),
('d0000000-0000-0000-0000-000000000008', '44444444-4444-4444-4444-444444444444', '888f9a1b-99bc-4959-aa85-7af82af92312', '1998-10-30'),
('d0000000-0000-0000-0000-000000000009', '44444444-4444-4444-4444-444444444444', '705ab6bc-489c-4700-b8e8-90c8b838e381', '1999-02-28'),

-- Paciente 5 (Fernanda/Fê): HPV
('d0000000-0000-0000-0000-000000000010', '55555555-5555-5555-5555-555555555555', '167aeb0d-80ff-49c9-9ce3-2658748ed03d', '2012-05-10'),

-- Paciente 7 (Juliana): Dupla Adulto (dT) e COVID-19
('d0000000-0000-0000-0000-000000000011', '77777777-7777-7777-7777-777777777777', 'c5f13b91-f4ca-42d9-8d23-32859d68873b', '2018-08-15'),
('d0000000-0000-0000-0000-000000000012', '77777777-7777-7777-7777-777777777777', '2aaf52ad-3438-4bcf-9638-69f4eda156ae', '2022-01-10'),

-- Paciente 10 (Letícia): Hepatite A e Tríplice Viral
('d0000000-0000-0000-0000-000000000013', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '180036f0-2cf9-4546-b5b8-ea4262d43843', '2006-09-10'),
('d0000000-0000-0000-0000-000000000014', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '00358b2b-d2f9-4344-83a9-022daead982b', '2006-09-10')
    ON CONFLICT (id) DO NOTHING;