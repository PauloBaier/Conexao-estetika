ALTER TABLE caixa
ALTER COLUMN status TYPE varchar(20)
USING status::text;

ALTER TABLE caixa
ALTER COLUMN fk_vendas_id DROP NOT NULL;

ALTER TABLE caixa
ALTER COLUMN data_fechamento DROP NOT NULL;