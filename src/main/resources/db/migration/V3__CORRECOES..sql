ALTER TABLE caixa
ALTER COLUMN status TYPE varchar(20)
USING status::text;

ALTER TABLE vendas
ALTER COLUMN status TYPE varchar(20)
USING status::text;

ALTER TABLE vendas
ALTER COLUMN pagamento TYPE varchar(20)
USING pagamento::text;

ALTER TABLE movimentacao_caixa
ALTER COLUMN tipo TYPE varchar(20)
USING tipo::text;

ALTER TABLE contas_receber
ALTER COLUMN status TYPE varchar(20)
USING status::text;

ALTER TABLE contas_pagar
ALTER COLUMN status TYPE varchar(20)
USING status::text;