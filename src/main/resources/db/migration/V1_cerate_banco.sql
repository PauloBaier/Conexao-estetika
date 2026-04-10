CREATE TYPE status_venda AS ENUM ('pendente', 'pago', 'cancelado');
CREATE TYPE forma_pagamento AS ENUM ('dinheiro', 'cartao', 'pix');
CREATE TYPE status_caixa AS ENUM ('aberto', 'fechado');
CREATE TYPE tipo_movimento AS ENUM ('entrada', 'saida');
CREATE TYPE status_conta AS ENUM ('pendente', 'pago', 'cancelado');

create table clientes(
    id serial not null primary key,
    nome varchar(100) not null,
    telefone varchar(11) not null,
    email varchar(254) not null,
    cpf varchar(11) not null
);

create table enderecos(
    id serial not null primary key,
    rua varchar(100) not null,
    bairro varchar(100) not null,
    numero varchar(10) not null,
    cep varchar(10) not null,
    fk_clientes_id int not null,

    foreign key (fk_clientes_id) references clientes(id)
);

create table vendas(
    id serial not null primary key,
    data date not null,
    status status_venda not null,
    valor_total decimal(10,2) not null,
    pagamento forma_pagamento not null,
    fk_clientes_id int not null,

    foreign key(fk_clientes_id) references clientes(id)
);

create table contas_receber(
    id serial not null primary key,
    descricao varchar(200) not null,
    valor decimal(10,2) not null,
    data_emissao date not null,
    data_vencimento date not null,
    data_pagamento date not null,
    status status_conta not null,
    fk_clientes_id int not null,
    fk_vendas_id int not null,

    foreign key(fk_clientes_id) references clientes(id),
    foreign key(fk_vendas_id) references vendas(id)
);

create table categorias(
    id serial not null primary key,
    nome varchar(100) not null
);

create table produtos(
    id serial not null primary key,
    nome varchar(100) not null,
    preco_compra decimal(10,2) not null,
    preco_venda decimal(10,2) not null,
    quantidade_estoque int not null,
    estoque_minimo int not null,
    fk_categorias_id int not null,

    foreign key(fk_categorias_id) references categorias(id)
);

create table itens_venda(
    id serial not null primary key,
    quantidade int not null,
    preco_unitario decimal(10,2) not null,
    total_item decimal(10,2) not null,
    fk_vendas_id int not null,
    fk_produtos_id int not null,

    foreign key(fk_vendas_id) references vendas(id),
    foreign key(fk_produtos_id) references produtos(id)
);

create table fornecedores(
    id serial not null primary key,
    nome varchar(100) not null,
    telefone varchar(11) not null,
    email varchar(254) not null,
    cnpj varchar(14) not null,
    razao_social varchar(100) not null
);

create table produtos_fornecedores(
    id serial not null primary key,
    fk_produtos_id int not null,
    fk_fornecedores_id int not null,

    foreign key(fk_produtos_id) references produtos(id),
    foreign key(fk_fornecedores_id) references fornecedores(id)
);

create table contas_pagar(
    id serial not null primary key,
    descricao varchar(200) not null,
    valor decimal(10,2) not null,
    data_emissao date not null,
    data_pagamento date not null,
    data_vencimento date not null,
    status status_conta not null,
    tipo_dispesas varchar(200) not null,
    fk_fornecedores_id int not null,

    foreign key(fk_fornecedores_id) references fornecedores(id)
);

create table caixa(
    id serial not null primary key,
    data_abertura date not null,
    valor_abertura decimal(10,2) not null,
    data_fechamento date not null,
    status status_caixa not null,
    fk_vendas_id int not null,

    foreign key(fk_vendas_id) references vendas(id)
);

create table movimentacao_caixa(
    id serial not null primary key,
    tipo tipo_movimento not null,
    valor decimal(10,2) not null,
    descricao varchar(200) not null,
    fk_caixa_id int not null,

    foreign key(fk_caixa_id) references caixa(id)
);


create table relatorio_geral(
    id serial not null primary key,
    data_relatorio date not null,
    total_entradas_caixa decimal(10,2) not null,
    total_saidas_caixa decimal(10,2) not null,
    saldo_caixa decimal(10,2) not null,
    total_vendas decimal(10,2) not null,
    numero_vendas int not null,
    total_clientes int not null,
    total_fornecedores int not null,
    total_produtos int not null,
    quantidade_estoque_total int not null,
    valor_estoque_total decimal(10,2) not null,
    observacoes varchar(200) not null,
    fk_caixa_id int not null,

    foreign key(fk_caixa_id) references caixa(id)
);

create or replace function atualizar_total_venda()
returns trigger as $$
begin
    update vendas
    set valor_total = (
        select coalesce(sum(total_item), 0)
        from itens_venda
        where fk_vendas_id = new.fk_vendas_id
    )
    where id = new.fk_vendas_id;

    return new;
end;
$$ language plpgsql;


create trigger trigger_atualizar_total_venda
after insert or update on itens_venda
for each row
execute function atualizar_total_venda();