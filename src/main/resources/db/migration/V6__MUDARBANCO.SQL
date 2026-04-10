ALTER TABLE contas_receber
ALTER COLUMN status TYPE varchar(20)
USING status::text;

ALTER TABLE contas_pagar
ALTER COLUMN status TYPE varchar(20)
USING status::text;