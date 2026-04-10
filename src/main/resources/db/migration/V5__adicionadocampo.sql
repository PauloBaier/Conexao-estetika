ALTER TABLE movimentacao_caixa
ADD COLUMN data_movimentacao timestamp;

ALTER TABLE movimentacao_caixa
ALTER COLUMN tipo TYPE varchar(20)
USING tipo::text;