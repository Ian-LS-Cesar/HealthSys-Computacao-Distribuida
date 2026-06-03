-- ==========================================
-- 1. POPULANDO TABELAS DE DOMÍNIO (TRIAGEM)
-- ==========================================

-- Inserindo Riscos (IDs Inteiros)
INSERT INTO risco (id, descricao) VALUES
                                      (1, 'Azul - Não Urgente'),
                                      (2, 'Verde - Pouco Urgente'),
                                      (3, 'Amarelo - Urgente'),
                                      (4, 'Laranja - Muito Urgente'),
                                      (5, 'Vermelho - Emergência')
ON CONFLICT (id) DO NOTHING;

-- Inserindo Status (IDs Inteiros)
INSERT INTO status (id, descricao) VALUES
                                       (1, 'ABERTA'),
                                       (2, 'EM_ATENDIMENTO'),
                                       (3, 'FINALIZADA'),
                                       (4, 'CANCELADA')
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 2. POPULANDO SINTOMAS
-- ==========================================

-- Nível 5 (Vermelho - Emergência | risco_id = 5)
INSERT INTO sintoma (id, descricao, risco_id) VALUES
                                                  (1, 'Parada cardiorrespiratória', 5),
                                                  (2, 'Obstrução de vias aéreas', 5),
                                                  (3, 'Obstrução total de vias aéreas (Asfixia)', 5),
                                                  (4, 'Hemorragia massiva não controlada', 5),
                                                  (5, 'Inconsciência ou rebaixamento de consciência', 5),
                                                  (6, 'Inconsciência ou Coma', 5),
                                                  (7, 'Convulsão ativa', 5),
                                                  (8, 'Choque (Sinais de má perfusão)', 5),
                                                  (9, 'Choque anafilático', 5),
                                                  (10, 'Trauma craniano grave com perda de consciência', 5),
                                                  (11, 'Trauma cranioencefálico grave', 5),
                                                  (12, 'Ferimento penetrante em tórax ou abdome', 5),
                                                  (13, 'Insuficiência respiratória grave', 5),
                                                  (14, 'Dispneia grave (Falta de ar extrema)', 5),
                                                  (15, 'Queimaduras de vias aéreas', 5),
                                                  (16, 'Grande queimadura', 5)
ON CONFLICT (id) DO NOTHING;

-- Nível 4 (Laranja - Muito Urgente | risco_id = 4)
INSERT INTO sintoma (id, descricao, risco_id) VALUES
                                                  (17, 'Dor precordial intensa (Suspeita de Infarto)', 4),
                                                  (18, 'Déficit neurológico súbito (Suspeita de AVC)', 4),
                                                  (19, 'Crise asmática grave (Uso de musculatura acessória)', 4),
                                                  (20, 'Grandes queimaduras (>20% de superfície corporal)', 4),
                                                  (21, 'Cetoacidose diabética (Alteração de consciência)', 4),
                                                  (22, 'Febre em recém-nascidos (Menores de 3 meses)', 4),
                                                  (23, 'Intoxicação exógena aguda', 4),
                                                  (24, 'Dor intensa súbita (Escala 8 a 10)', 4),
                                                  (25, 'Dor no peito intensa (Suspeita de IAM)', 4),
                                                  (26, 'Arritmias cardíacas instáveis', 4),
                                                  (27, 'Fraturas expostas', 4)
ON CONFLICT (id) DO NOTHING;

-- Nível 3 (Amarelo - Urgente | risco_id = 3)
INSERT INTO sintoma (id, descricao, risco_id) VALUES
                                                  (28, 'Dor moderada aguda (Escala 4 a 7)', 3),
                                                  (29, 'Vômitos persistentes', 3),
                                                  (30, 'Vômitos e diarreia persistentes', 3),
                                                  (31, 'Febre alta (Acima de 39°C)', 3),
                                                  (32, 'Febre persistente (Acima de 38.5°C)', 3),
                                                  (33, 'Desmaio (Síncope) com recuperação', 3),
                                                  (34, 'Síncope (Desmaio) com recuperação total', 3),
                                                  (35, 'Alteração súbita de fala ou visão', 3),
                                                  (36, 'Crise de ansiedade grave', 3),
                                                  (37, 'Cólicas renais ou biliares', 3),
                                                  (38, 'Hemorragia moderada controlada', 3),
                                                  (39, 'Hipertensão arterial sintomática', 3),
                                                  (40, 'Crise hipertensiva sintomática', 3),
                                                  (41, 'Fraturas fechadas', 3),
                                                  (42, 'Fraturas fechadas sem deformidade grave', 3),
                                                  (43, 'Paciente com mobilidade reduzida aguda', 3),
                                                  (44, 'Crise asmática moderada', 3)
ON CONFLICT (id) DO NOTHING;

-- Nível 2 (Verde - Pouco Urgente | risco_id = 2)
INSERT INTO sintoma (id, descricao, risco_id) VALUES
                                                  (45, 'Dor leve', 2),
                                                  (46, 'Sintomas gripais sem falta de ar', 2),
                                                  (47, 'Sintomas gripais estáveis (Sem falta de ar)', 2),
                                                  (48, 'Diarreia sem desidratação', 2),
                                                  (49, 'Vômito isolado', 2),
                                                  (50, 'Vômito isolado (Sem sinais de desidratação)', 2),
                                                  (51, 'Entorses e pequenas contusões', 2),
                                                  (52, 'Cefaleia leve', 2),
                                                  (53, 'Cefaleia (Dor de cabeça) leve e comum', 2),
                                                  (54, 'Erupções cutâneas simples', 2),
                                                  (55, 'Reação alérgica leve (Urticária localizada)', 2),
                                                  (56, 'Lombalgia (Dor nas costas) leve ou moderada', 2),
                                                  (57, 'Abscessos sem febre', 2),
                                                  (58, 'Conjuntivite ou irritação ocular', 2),
                                                  (59, 'Disúria (Ardor ao urinar) sem febre', 2)
ON CONFLICT (id) DO NOTHING;

-- Nível 1 (Azul - Não Urgente | risco_id = 1)
INSERT INTO sintoma (id, descricao, risco_id) VALUES
                                                  (60, 'Dores crônicas (Longa duração)', 1),
                                                  (61, 'Dores crônicas (Duração de meses ou anos)', 1),
                                                  (62, 'Troca de curativos', 1),
                                                  (63, 'Troca de curativos simples', 1),
                                                  (64, 'Retirada de pontos', 1),
                                                  (65, 'Solicitação de receitas', 1),
                                                  (66, 'Renovação de receitas', 1),
                                                  (67, 'Avaliação de exames laboratoriais', 1),
                                                  (68, 'Pequenas escoriações e arranhões', 1),
                                                  (69, 'Pequenas escoriações ou arranhões', 1),
                                                  (70, 'Encaminhamentos médicos', 1),
                                                  (71, 'Encaminhamentos para especialistas', 1),
                                                  (72, 'Solicitação de atestados ou laudos', 1),
                                                  (73, 'Avaliação de lesões de pele antigas', 1),
                                                  (74, 'Orientações médicas gerais', 1)
ON CONFLICT (id) DO NOTHING;