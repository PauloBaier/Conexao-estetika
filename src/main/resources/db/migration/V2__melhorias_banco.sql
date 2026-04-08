-- V2__melhorias_banco.sql

-- Drop tabelas antigas (somente se não tiver dados)
DROP TABLE IF EXISTS itens_venda CASCADE;
DROP TABLE IF EXISTS vendas CASCADE;
DROP TABLE IF EXISTS produto_fornecedor CASCADE;
DROP TABLE IF EXISTS produtos CASCADE;
DROP TABLE IF EXISTS categorias CASCADE;
DROP TABLE IF EXISTS endereco CASCADE;
DROP TABLE IF EXISTS clientes CASCADE;
DROP TABLE IF EXISTS fornecedores CASCADE;
DROP TABLE IF EXISTS contas_receber CASCADE;
DROP TABLE IF EXISTS contas_pagar CASCADE;
DROP TABLE IF EXISTS movimentacao_caixa CASCADE;
DROP TABLE IF EXISTS caixa CASCADE;
DROP TABLE IF EXISTS relatorio_geral CASCADE;

-- ENUMs
CREATE TYPE status_venda AS ENUM ('pendente', 'pago', 'cancelado');
CREATE TYPE forma_pagamento AS ENUM ('dinheiro', 'cartao', 'pix');
CREATE TYPE status_caixa AS ENUM ('aberto', 'fechado');
CREATE TYPE tipo_movimento AS ENUM ('entrada', 'saida');
CREATE TYPE status_conta AS ENUM ('pendente', 'pago', 'cancelado');

-- Tabelas
CREATE TABLE clientes (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    telefone varchar(11) NOT NULL,
    email varchar(254) NOT NULL,
    cpf varchar(11) NOT NULL
);

CREATE TABLE fornecedores (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    telefone varchar(11) NOT NULL,
    email varchar(254) NOT NULL,
    cnpj varchar(14) NOT NULL,
    razaosocial varchar(100) NOT NULL
);

CREATE TABLE endereco (
    id serial PRIMARY KEY,
    rua varchar(100) NOT NULL,
    bairro varchar(100) NOT NULL,
    numero varchar(10) NOT NULL,
    fk_clientes_id int NOT NULL REFERENCES clientes(id)
);

CREATE TABLE categorias (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL
);

CREATE TABLE produtos (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    precocompra decimal(10,2) NOT NULL,
    precovenda decimal(10,2) NOT NULL,
    quantidadeestoque int NOT NULL,
    estoqueminimo int NOT NULL,
    fk_categorias_id int NOT NULL REFERENCES categorias(id)
);

CREATE TABLE produto_fornecedor (
    id serial PRIMARY KEY,
    id_produto int NOT NULL REFERENCES produtos(id),
    id_fornecedor int NOT NULL REFERENCES fornecedores(id)
);

CREATE TABLE vendas (
    id serial PRIMARY KEY,
    data timestamp NOT NULL,
    status status_venda NOT NULL,
    valor_total decimal(10,2) NOT NULL,
    forma_pagamento forma_pagamento NOT NULL,
    fk_cliente_id int NOT NULL REFERENCES clientes(id)
);

CREATE TABLE itens_venda (
    id serial PRIMARY KEY,
    fk_venda_id int NOT NULL REFERENCES vendas(id),
    fk_produto_id int NOT NULL REFERENCES produtos(id),
    quantidade int NOT NULL,
    preco_unitario decimal(10,2) NOT NULL,
    total_item decimal(10,2) NOT NULL
);

CREATE TABLE contas_receber (
    id serial PRIMARY KEY,
    descricao varchar(150),
    valor decimal(10,2) NOT NULL,
    data_emissao date NOT NULL,
    data_vencimento date NOT NULL,
    data_pagamento date,
    status status_conta NOT NULL,
    fk_cliente_id int NOT NULL REFERENCES clientes(id),
    fk_venda_id int NOT NULL REFERENCES vendas(id)
);

CREATE TABLE contas_pagar (
    id serial PRIMARY KEY,
    descricao varchar(150),
    valor decimal(10,2) NOT NULL,
    data_emissao date NOT NULL,
    data_vencimento date NOT NULL,
    data_pagamento date,
    status status_conta NOT NULL,
    fk_fornecedor_id int NOT NULL REFERENCES fornecedores(id),
    tipo_despesa varchar(50) NOT NULL
);

CREATE TABLE caixa (
    id serial PRIMARY KEY,
    data_abertura timestamp NOT NULL,
    valor_abertura decimal(10,2) NOT NULL,
    data_fechamento timestamp,
    valor_fechamento decimal(10,2),
    status status_caixa NOT NULL DEFAULT 'aberto'
);

CREATE TABLE movimentacao_caixa (
    id serial PRIMARY KEY,
    fk_caixa_id int NOT NULL REFERENCES caixa(id),
    tipo tipo_movimento NOT NULL,
    valor decimal(10,2) NOT NULL,
    descricao varchar(150),
    data_movimentacao timestamp NOT NULL DEFAULT now()
);

CREATE TABLE relatorio_geral (
    id serial PRIMARY KEY,
    data_relatorio date NOT NULL,
    fk_caixa_id int REFERENCES caixa(id),
    total_entradas_caixa decimal(10,2) NOT NULL,
    total_saidas_caixa decimal(10,2) NOT NULL,
    saldo_caixa decimal(10,2) NOT NULL,
    total_vendas decimal(10,2) NOT NULL,
    numero_vendas int NOT NULL,
    total_clientes int NOT NULL,
    total_fornecedores int NOT NULL,
    total_produtos int NOT NULL,
    quantidade_estoque_total int NOT NULL,
    valor_total_estoque decimal(10,2) NOT NULL,
    observacoes varchar(255)
);