
-- TIPOS ENUM

CREATE TYPE status_venda AS ENUM ('PENDENTE', 'PAGO', 'CANCELADO');
CREATE TYPE forma_pagamento AS ENUM ('DINHEIRO', 'CARTAO', 'PIX');
CREATE TYPE status_caixa AS ENUM ('ABERTO', 'FECHADO');
CREATE TYPE tipo_movimento AS ENUM ('ENTRADA', 'SAIDA');
CREATE TYPE status_conta AS ENUM ('PENDENTE', 'PAGO', 'CANCELADO');



-- TABELA CLIENTES

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(254) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE
);



-- TABELA ENDERECOS

CREATE TABLE enderecos (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    fk_clientes_id INT NOT NULL,
    CONSTRAINT fk_endereco_cliente
        FOREIGN KEY (fk_clientes_id) REFERENCES clientes(id)
        ON DELETE CASCADE
);



-- TABELA CATEGORIAS

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);



-- TABELA FORNECEDORES

CREATE TABLE fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(254) NOT NULL,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    razao_social VARCHAR(100) NOT NULL
);



-- TABELA PRODUTOS

CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco_compra DECIMAL(10,2) NOT NULL CHECK (preco_compra > 0),
    preco_venda DECIMAL(10,2) NOT NULL CHECK (preco_venda > 0),
    quantidade_estoque INT NOT NULL CHECK (quantidade_estoque >= 0),
    estoque_minimo INT NOT NULL CHECK (estoque_minimo >= 0),
    fk_categorias_id INT NOT NULL,
    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (fk_categorias_id) REFERENCES categorias(id)
);



-- TABELA PRODUTOS_FORNECEDORES

CREATE TABLE produtos_fornecedores (
    id SERIAL PRIMARY KEY,
    fk_produtos_id INT NOT NULL,
    fk_fornecedores_id INT NOT NULL,
    CONSTRAINT fk_pf_produto
        FOREIGN KEY (fk_produtos_id) REFERENCES produtos(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_pf_fornecedor
        FOREIGN KEY (fk_fornecedores_id) REFERENCES fornecedores(id)
        ON DELETE CASCADE,
    CONSTRAINT uk_produto_fornecedor UNIQUE (fk_produtos_id, fk_fornecedores_id)
);



-- TABELA CAIXA

CREATE TABLE caixa (
    id SERIAL PRIMARY KEY,
    data_abertura DATE NOT NULL,
    valor_abertura DECIMAL(10,2) NOT NULL CHECK (valor_abertura >= 0),
    saldo_atual DECIMAL(10,2) NOT NULL CHECK (saldo_atual >= 0),
    data_fechamento DATE,
    status status_caixa NOT NULL
);



-- TABELA MOVIMENTACAO_CAIXA

CREATE TABLE movimentacao_caixa (
    id SERIAL PRIMARY KEY,
    fk_caixa_id INT NOT NULL,
    tipo tipo_movimento NOT NULL,
    valor DECIMAL(10,2) NOT NULL CHECK (valor > 0),
    descricao VARCHAR(200) NOT NULL,
    data_movimentacao TIMESTAMP NOT NULL,
    CONSTRAINT fk_movimentacao_caixa
        FOREIGN KEY (fk_caixa_id) REFERENCES caixa(id)
);



-- TABELA VENDAS

CREATE TABLE vendas (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL DEFAULT 0 CHECK (valor_total >= 0),
    status status_venda NOT NULL,
    pagamento forma_pagamento,
    fk_clientes_id INT,
    CONSTRAINT fk_venda_cliente
        FOREIGN KEY (fk_clientes_id) REFERENCES clientes(id)
);



-- TABELA ITENS_VENDA

CREATE TABLE itens_venda (
    id SERIAL PRIMARY KEY,
    quantidade INT NOT NULL CHECK (quantidade > 0),
    preco_unitario DECIMAL(10,2) NOT NULL CHECK (preco_unitario > 0),
    total_item DECIMAL(10,2) NOT NULL CHECK (total_item >= 0),
    fk_vendas_id INT NOT NULL,
    fk_produtos_id INT NOT NULL,
    CONSTRAINT fk_item_venda
        FOREIGN KEY (fk_vendas_id) REFERENCES vendas(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_item_produto
        FOREIGN KEY (fk_produtos_id) REFERENCES produtos(id)
);



-- TABELA CONTAS_RECEBER

CREATE TABLE contas_receber (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    valor DECIMAL(10,2) NOT NULL CHECK (valor > 0),
    data_emissao DATE NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    status status_conta NOT NULL,
    fk_clientes_id INT,
    fk_vendas_id INT NOT NULL,
    CONSTRAINT fk_cr_cliente
        FOREIGN KEY (fk_clientes_id) REFERENCES clientes(id),
    CONSTRAINT fk_cr_venda
        FOREIGN KEY (fk_vendas_id) REFERENCES vendas(id)
);



-- TABELA CONTAS_PAGAR

CREATE TABLE contas_pagar (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    valor DECIMAL(10,2) NOT NULL CHECK (valor > 0),
    data_emissao DATE NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    tipo_dispesas VARCHAR(200) NOT NULL,
    status status_conta NOT NULL,
    fk_fornecedores_id INT NOT NULL,
    CONSTRAINT fk_cp_fornecedor
        FOREIGN KEY (fk_fornecedores_id) REFERENCES fornecedores(id)
);



-- FUNÇÃO PARA ATUALIZAR TOTAL DA VENDA

CREATE OR REPLACE FUNCTION atualizar_total_venda()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE vendas
    SET valor_total = (
        SELECT COALESCE(SUM(total_item), 0)
        FROM itens_venda
        WHERE fk_vendas_id = NEW.fk_vendas_id
    )
    WHERE id = NEW.fk_vendas_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_total_venda
AFTER INSERT OR UPDATE ON itens_venda
FOR EACH ROW
EXECUTE FUNCTION atualizar_total_venda();



-- FUNÇÃO PARA VALIDAR CAIXA ABERTO NA MOVIMENTAÇÃO

CREATE OR REPLACE FUNCTION validar_caixa_aberto()
RETURNS TRIGGER AS $$
DECLARE
    status_caixa_atual status_caixa;
BEGIN
    SELECT status
    INTO status_caixa_atual
    FROM caixa
    WHERE id = NEW.fk_caixa_id;

    IF status_caixa_atual IS NULL THEN
        RAISE EXCEPTION 'Caixa não encontrado.';
    END IF;

    IF status_caixa_atual <> 'ABERTO' THEN
        RAISE EXCEPTION 'O caixa está fechado.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_caixa_aberto
BEFORE INSERT OR UPDATE ON movimentacao_caixa
FOR EACH ROW
EXECUTE FUNCTION validar_caixa_aberto();



-- FUNÇÃO PARA VALIDAR DATAS DE CONTAS A PAGAR

CREATE OR REPLACE FUNCTION validar_datas_conta_pagar()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.data_vencimento < NEW.data_emissao THEN
        RAISE EXCEPTION 'Data de vencimento não pode ser anterior à emissão em contas_pagar.';
    END IF;

    IF NEW.data_pagamento IS NOT NULL AND NEW.data_pagamento < NEW.data_emissao THEN
        RAISE EXCEPTION 'Data de pagamento inválida em contas_pagar.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_datas_conta_pagar
BEFORE INSERT OR UPDATE ON contas_pagar
FOR EACH ROW
EXECUTE FUNCTION validar_datas_conta_pagar();



-- FUNÇÃO PARA VALIDAR DATAS DE CONTAS A RECEBER

CREATE OR REPLACE FUNCTION validar_datas_conta_receber()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.data_vencimento < NEW.data_emissao THEN
        RAISE EXCEPTION 'Data de vencimento não pode ser anterior à emissão em contas_receber.';
    END IF;

    IF NEW.data_pagamento IS NOT NULL AND NEW.data_pagamento < NEW.data_emissao THEN
        RAISE EXCEPTION 'Data de pagamento inválida em contas_receber.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_datas_conta_receber
BEFORE INSERT OR UPDATE ON contas_receber
FOR EACH ROW
EXECUTE FUNCTION validar_datas_conta_receber();



-- FUNÇÃO PARA IMPEDIR MAIS DE UM CAIXA ABERTO

CREATE OR REPLACE FUNCTION validar_um_caixa_aberto()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status = 'ABERTO' THEN
        IF EXISTS (
            SELECT 1
            FROM caixa
            WHERE status = 'ABERTO'
              AND (TG_OP = 'INSERT' OR id <> NEW.id)
        ) THEN
            RAISE EXCEPTION 'Já existe um caixa aberto.';
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_um_caixa_aberto
BEFORE INSERT OR UPDATE ON caixa
FOR EACH ROW
EXECUTE FUNCTION validar_um_caixa_aberto();