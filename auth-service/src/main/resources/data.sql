-- ==========================================
-- 1. POPULANDO TABELAS DE DOMÍNIO (PERFIS E ESPECIALIDADES)
-- ==========================================

-- Inserindo Perfis (IDs Inteiros)
INSERT INTO perfil (id, descricao) VALUES
                                       (1, 'Recepcionista'),
                                       (2, 'Equipe de Triagem'),
                                       (3, 'Enfermeiro(a)'),
                                       (4, 'Médico(a)'),
                                       (5, 'Administração'),
                                       (6, 'ADMIN')
ON CONFLICT (id) DO NOTHING;

-- Inserindo Especialidades (IDs Inteiros)
INSERT INTO especialidade (id, descricao) VALUES
                                              (1, 'Clínica Geral'),
                                              (2, 'Cardiologia'),
                                              (3, 'Dermatologia'),
                                              (4, 'Endocrinologia'),
                                              (5, 'Gastroenterologia'),
                                              (6, 'Ginecologia e Obstetrícia'),
                                              (7, 'Neurologia'),
                                              (8, 'Oftalmologia'),
                                              (9, 'Ortopedia'),
                                              (10, 'Otorrinolaringologia'),
                                              (11, 'Pediatria'),
                                              (12, 'Psiquiatria'),
                                              (13, 'Urologia')
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 2. POPULANDO USUÁRIO PADRÃO
-- ==========================================
-- UUID estritamente hexadecimal.
-- Senha utilizando o hash BCrypt fornecido.
INSERT INTO usuario (id, nome, email, senha, data_nascimento, perfil_id, especialidade_id) VALUES
    ('11111111-1111-1111-1111-111111111111',
     'Administrador',
     'admin@healthsys.com',
     '$2a$10$ReFcBCJ7Q9LuQS/Oe8e6BeTp84sLjeU41gK1a..A0EWmvTR02nVbi',
     '1990-01-01',
     6,
     1)
ON CONFLICT (id) DO NOTHING;