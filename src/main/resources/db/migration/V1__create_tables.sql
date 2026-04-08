create table clientes (
    id bigint not null primary key,
    nome varchar(100) not null,
    telefone varchar(11) not null,
    email varchar(254) not null,
    cpf varchar(11) not null
);

create table fornecedores (
    id bigint not null primary key,
    nome varchar(100) not null,
    telefone varchar(11) not null,
    email varchar(254) not null,
    cnpj varchar(14) not null,
    razaosocial varchar(100) not null
);

create table endereco (
    id serial not null primary key,
    rua varchar(100) not null,
    bairro varchar(100) not null,
    numero varchar(10) not null,
    fk_clientes_id bigint not null,

    foreign key (fk_clientes_id) references clientes(id)
);


create table categorias (
    id bigint not null primary key,
    nome varchar(100) not null
);

create table produtos (
    id bigint not null primary key,
    nome varchar(100) not null,
    precocompra decimal(10,2) not null,
    precovenda decimal(10,2) not null,
    quantidadeestoque int not null,
    estoqueminimo int not null,
    fk_categorias_id bigint not null,

    foreign key (fk_categorias_id) references categorias(id)
);

create table produto_fornecedor (
    id_produto bigint not null,
    id_fornecedor bigint not null,

    primary key (id_produto, id_fornecedor),

    foreign key (id_produto) references produtos(id),
    foreign key (id_fornecedor) references fornecedores(id)
);

create table vendas (
    id bigint not null primary key,
    data timestamp not null,
    status varchar(20) not null, -- pendente, pago, cancelado
    valor_total decimal(10,2) not null,
    fk_cliente_id bigint not null,

    foreign key (fk_cliente_id) references clientes(id)
);

create table itens_venda (
    id bigint not null primary key,
    fk_venda_id bigint not null,
    fk_produto_id bigint not null,
    quantidade int not null,
    preco_unitario decimal(10,2) not null,
    total_item decimal(10,2) not null,

    foreign key (fk_venda_id) references vendas(id),
    foreign key (fk_produto_id) references produtos(id)
);

create table contas_receber (
    id bigint not null primary key,
    descricao varchar(150),
    valor decimal(10,2) not null,
    data_emissao date not null,
    data_vencimento date not null,
    data_pagamento date not null,
    status varchar(20) not null, -- pendente, pago, cancelado
    fk_cliente_id bigint not null,
    fk_venda_id bigint not null,

    foreign key (fk_cliente_id) references clientes(id),
    foreign key (fk_venda_id) references vendas(id)
);

create table contas_pagar (
    id bigint not null primary key,
    descricao varchar(150),
    valor decimal(10,2) not null,
    data_emissao date not null,
    data_vencimento date not null,
    data_pagamento date not null,
    status varchar(20) not null, -- pendente, pago, cancelado
    fk_fornecedor_id bigint not null,
    tipo_despesa varchar(50) not null, -- energia, aluguel, etc

    foreign key (fk_fornecedor_id) references fornecedores(id)
);