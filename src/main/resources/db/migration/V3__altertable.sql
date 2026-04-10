ALTER TABLE vendas
ALTER COLUMN pagamento TYPE varchar(20)
USING pagamento::text;

ALTER TABLE vendas
ALTER COLUMN status TYPE varchar(20)
USING status::text;