SET default_storage_engine=InnoDB;

CREATE DATABASE IF NOT EXISTS gestao_academica;
USE gestao_academica;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('aluno', 'professor', 'admin') NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    nome_completo VARCHAR(255) NOT NULL,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    data_nascimento DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE professores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    nome_completo VARCHAR(255) NOT NULL,
    siape VARCHAR(50) UNIQUE,
    departamento VARCHAR(100),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE administradores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE,
    nome_completo VARCHAR(255) NOT NULL,
    cargo VARCHAR(100),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    codigo_curso VARCHAR(20) UNIQUE
);

CREATE TABLE disciplinas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT,
    nome VARCHAR(255) NOT NULL,
    codigo_disciplina VARCHAR(20) NOT NULL UNIQUE,
    carga_horaria INT,
    FOREIGN KEY (curso_id) REFERENCES cursos(id) ON DELETE SET NULL
);

CREATE TABLE periodos_letivos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identificador VARCHAR(20) NOT NULL UNIQUE,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL
);

CREATE TABLE turmas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    disciplina_id INT NOT NULL,
    periodo_letivo_id INT NOT NULL,
    professor_id INT,
    codigo_turma VARCHAR(20) NOT NULL,
    FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id) ON DELETE RESTRICT,
    FOREIGN KEY (periodo_letivo_id) REFERENCES periodos_letivos(id) ON DELETE RESTRICT,
    FOREIGN KEY (professor_id) REFERENCES professores(id) ON DELETE SET NULL,
    UNIQUE KEY uk_turma (disciplina_id, periodo_letivo_id, codigo_turma)
);

CREATE TABLE matriculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    turma_id INT NOT NULL,
    media_final DECIMAL(4, 2),
    frequencia DECIMAL(5, 2),
    status ENUM('cursando', 'aprovado', 'reprovado', 'trancado') NOT NULL DEFAULT 'cursando',
    FOREIGN KEY (aluno_id) REFERENCES alunos(id) ON DELETE CASCADE,
    FOREIGN KEY (turma_id) REFERENCES turmas(id) ON DELETE CASCADE,
    UNIQUE KEY uk_aluno_turma (aluno_id, turma_id)
);

CREATE TABLE avaliacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    turma_id INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    peso DECIMAL(3, 2) NOT NULL DEFAULT 1.0,
    data_avaliacao DATE,
    FOREIGN KEY (turma_id) REFERENCES turmas(id) ON DELETE CASCADE
);

CREATE TABLE notas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    avaliacao_id INT NOT NULL,
    matricula_id INT NOT NULL,
    valor_nota DECIMAL(4, 2) NOT NULL,
    FOREIGN KEY (avaliacao_id) REFERENCES avaliacoes(id) ON DELETE CASCADE,
    FOREIGN KEY (matricula_id) REFERENCES matriculas(id) ON DELETE CASCADE,
    UNIQUE KEY uk_avaliacao_matricula (avaliacao_id, matricula_id)
);