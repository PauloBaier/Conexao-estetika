CREATE TABLE usuarios (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(254) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  perfil VARCHAR(20) NOT NULL CHECK (perfil IN ('gerente', 'funcionario', 'administrador')),
  ativo BOOLEAN NOT NULL DEFAULT true,
  criado_em TIMESTAMPTZ DEFAULT now()
);


-- Quem abriu/fechou o caixa
ALTER TABLE caixa
ADD COLUMN fk_usuarios_id INT REFERENCES usuarios(id);

-- Quem fez a movimentação no caixa
ALTER TABLE movimentacao_caixa
ADD COLUMN fk_usuarios_id INT REFERENCES usuarios(id);

-- Quem realizou a venda
ALTER TABLE vendas
ADD COLUMN fk_usuarios_id INT REFERENCES usuarios(id);