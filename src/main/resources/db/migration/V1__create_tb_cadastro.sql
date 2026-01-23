-- V1__Criar_tabelas_iniciais.sql

-- 1. Criação da tabela de Missões (Tabela referenciada)
CREATE TABLE tb_missoes (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(255),
    DIFICULDADE VARCHAR(255)
);

-- 2. Criação da tabela de Cadastro (NinjaModel)
CREATE TABLE tb_cadastro (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100),
    EMAIL VARCHAR(255) NOT NULL UNIQUE, -- Coluna 'unique = true' no model
    IMG_URL VARCHAR(2048),
    IDADE INT,
    MISSOES_ID BIGINT, -- Coluna que armazena a Chave Estrangeira

    -- Definição da Chave Estrangeira
    FOREIGN KEY (MISSOES_ID) REFERENCES tb_missoes(ID)
);