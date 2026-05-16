# Conexão Estétika

Um sistema desktop completo focado na gestão comercial, controle de estoque crítico e fluxo de caixa, desenvolvido sob medida para a clínica Conexão Estética. O projeto nasceu como um Projeto Integrador de Extensão, com o objetivo de resolver as dores reais de gerenciamento de um estabelecimento, unificando o Ponto de Venda (PDV) com o controle financeiro de ponta a ponta.

---

## 🛠️ O que o sistema faz na prática

* **PDV Ágil com Controle de Caixa:** Fluxo completo de abertura, movimentações diárias (suprimento e sangria), fechamento de caixa e tela de vendas fluida. O sistema calcula o troco em tempo real e atualiza o estoque instantaneamente.
* **Alertas Visuais de Estoque Crítico:** A listagem de produtos identifica automaticamente os itens que atingiram ou ficaram abaixo do estoque mínimo definido, destacando as linhas em vermelho para avisar o operador sobre a necessidade de reposição.
* **Segurança por Níveis de Acesso (Gerência vs. Operador):** Operações sensíveis de caixa (como retiradas de dinheiro ou abertura) feitas por um usuário com perfil de funcionário disparam um bloqueio em tela. O sistema exige a autenticação manual e imediata de um gerente/administrador no próprio local para autorizar a ação.
* **Módulo Financeiro Amarrado:** Controle rigoroso de Contas a Pagar (despesas e compras de insumos) e Contas a Receber. A quitação de qualquer conta dá baixa automática e atualiza o saldo real do caixa aberto no momento.
* **Relatórios e Filtros Avançados:** Filtros customizados por datas e status para extrair dados limpos sobre faturamento, inadimplência (contas pendentes) e curva de produtos em falta.

---

## 🏗️ Engenharia do Projeto e Decisões de Arquitetura

O software foi construído do zero priorizando a separação estrita de responsabilidades (padrão MVC), garantindo que a aplicação seja fácil de manter e que novas interfaces (como uma versão web ou mobile) possam aproveitar as mesmas regras de negócio.

### View (Interface Gráfica)
Desenvolvida em **Java Swing** com uma identidade visual flat customizada (focada em tons de verde esmeralda). As telas são totalmente "burras": elas apenas capturam o que o usuário digita ou clica, enviam para o backend e exibem o resultado ou a mensagem de erro apropriada. Nenhuma validação lógica ou cálculo matemático acontece dentro do código da interface.

### Service (Camada de Negócio)
Centraliza todo o comportamento e regras do sistema. É aqui que o software valida se o saldo em caixa é suficiente antes de permitir uma sangria, se o estoque comporta uma venda ou se o CPF/CNPJ digitado é válido. Se algo estiver errado, o service lança uma exceção controlada para a interface tratar.

### Injeção de Dependências Manual
Para manter o projeto leve e entender o fluxo de acoplamento de ponta a ponta, a resolução das dependências entre Repositórios, Services e Views foi feita de forma manual dentro da classe principal `Main.java`, sem o uso de frameworks pesados de terceiros.

### Banco de Dados e Migrações
* **Hibernate & JPA:** Utilizados para mapear o modelo de domínio do banco de dados, gerenciar transações de forma segura e blindar o sistema contra ataques de *SQL Injection*.
* **Flyway DB:** Implementado para fazer o versionamento programático do banco. Isso garante que qualquer alteração na estrutura das tabelas seja replicada de forma automática e idêntica assim que o sistema é iniciado em uma nova máquina.

---

## 🚀 Como Rodar o Projeto Localmente

### Pré-requisitos
* Java JDK 17 ou superior.
* Servidor de Banco de Dados (configurado por padrão para PostgreSQL).

### Configuração
1. Configure as credenciais do seu banco de dados local no arquivo de propriedades/configuração do Hibernate (ex: `hibernate.cfg.xml`):
```xml
<property name="jakarta.persistence.jdbc.url">jdbc:postgresql://localhost:5432/conexao_estetika</property>
<property name="jakarta.persistence.jdbc.user">seu_usuario</property>
<property name="jakarta.persistence.jdbc.password">sua_senha</property>
