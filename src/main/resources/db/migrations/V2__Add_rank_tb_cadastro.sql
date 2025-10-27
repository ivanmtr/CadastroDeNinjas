-- V2: MIgrations para adicionar a coluna de RANK na tabela de cadastro

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255) NULL;