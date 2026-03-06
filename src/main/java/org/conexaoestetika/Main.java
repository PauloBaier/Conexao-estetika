package org.conexaoestetika;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Venda");
            System.out.println("3 - Financeiro");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    menuCadastros();
                    break;
                case 2:
                    menuVenda();
                    break;
                case 3:
                    menuFinanceiro();
                    break;
                case 4:
                    menuRelatorios();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public static void menuCadastros() {
        int opcao;

        do {
            System.out.println("\n===== MENU CADASTROS =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Fornecedor");
            System.out.println("3 - Cadastrar Produto");
            System.out.println("4 - Listar Clientes");
            System.out.println("5 - Listar Fornecedores");
            System.out.println("6 - Listar Produtos");
            System.out.println("7 - Remover Cliente");
            System.out.println("8 - Remover Fornecedor");
            System.out.println("9 - Remover Produto");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opcão: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                  cadastroCliente(sistema);
                    break;
                case 2:
                    cadastroFornecedor(sistema);
                    break;
                case 3:
                    cadastroProduto(sistema);
                    break;
                case 4:
                    listarCliente(sistema);
                    break;
                case 5:
                    listarFornecedor(sistema);
                    break;
                case 6:
                    listarProduto(sistema);
                    break;
                case 7:
                    removerCliente(sistema);
                    break;
                case 8:
                    removerFornecedor(sistema);
                    break;
                case 9:
                    removerProduto(sistema);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public static void menuVenda() {
        int opcao;

        do {
            System.out.println("\n===== MENU VENDA =====");
            System.out.println("1 - Nova Venda");
            System.out.println("2 - Cancelar Venda");
            System.out.println("0 - Voltar");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    novaVenda(sistema);
                    break;
                case 2:
                    System.out.println("Venda cancelada!");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public static void menuFinanceiro() {
        int opcao;

        do {
            System.out.println("\n===== MENU FINANCEIRO =====");
            System.out.println("1 - Contas a Receber");
            System.out.println("2 - Contas a Pagar");
            System.out.println("0 - Voltar");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Marcar conta como paga...");
                    break;
                case 2:
                    System.out.println("Cadastrar Conta a Pagar...");
                    System.out.println("Marcar Conta como paga...");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public static void menuRelatorios() {
        int opcao;

        do {
            System.out.println("\n===== MENU RELATÓRIOS =====");
            System.out.println("1 - Contas a Receber / Vendas");
            System.out.println("2 - Contas a Pagar");
            System.out.println("3 - Produtos (Estoque)");
            System.out.println("0 - Voltar");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Filtrar por data e status...");
                    break;
                case 2:
                    System.out.println("Filtrar por data e status...");
                    break;
                case 3:
                    System.out.println("Filtro: Todos / Estoque Baixo...");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public void cadastroCliente(Sistema sistema) {

        System.out.print("ID: " + (sistema.cadastroCliente.getUltimoId() + 1));

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("CPF (11 números): ");
        String cpf = sc.nextLine();

        System.out.print("Dinheiro Gasto: ");
        double dinheiroGasto = sc.nextDouble();

        sistema.novoCadastroCliente(nome, telefone, email, cpf, dinheiroGasto);

        System.out.println("Cliente cadastro com sucesso!");
    }

    public void cadastroFornecedor(Sistema sistema) {

        System.out.print("ID: " + (sistema.cadastroFornecedor.getUltimoId() + 1));

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("CNPJ(14 números): ");
        String cnpj = sc.nextLine();

        System.out.print("Razão Social: ");
        String razaoSocial = sc.nextLine();

        sistema.novoCadastroFornecedor(nome, telefone, email, cnpj, razaoSocial);

        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    public void cadastroProduto(Sistema sistema) {

        System.out.print("ID: " + (sistema.cadastroProduto.getUltimoId() + 1));

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.print("Preço de Custo: ");
        double precoCusto = sc.nextDouble();

        System.out.print("Preço de Venda: ");
        double precoVenda = sc.nextDouble();

        System.out.print("Quantidade de Estoque: ");
        int qntdEstoque = sc.nextInt();

        System.out.print("Estoque Minimo: ");
        int estoqueMinimo = sc.nextInt();

        System.out.println("Fornecedor: ");
        Fornecedor fornecedor = escolherFornecedor();

        sistema.novoCadastroProduto(nome, descricao, precoCusto, precoVenda, qntdEstoque, estoqueMinimo, fornecedor);

        System.out.println("Pproduto cadastrado com sucesso!");

    }

    public Fornecedor escolherFornecedor() {

        for (Fornecedor f : listaFornecedores()) {
            System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome());
        }

        System.out.print("Digite o ID do fornecedor: ");
        int idEscolhido = sc.nextInt();

        for (Fornecedor f : listaFornecedores()) {
            if (f.getId() == idEscolhido) {
                return f;
            }
        }

        System.out.println("Fornecedor não encontrado.");
        return null;
    }

    public void listarCliente(Sistema sistema) {

        for(Cliente c : sistema.cadastroCliente.listarTodos()) {
            System.out.println("ID: " + c.getId()
                    + "| Nome: " + c.getNome()
                    + "| Telefone" + c.getTelefone()
                    + "| Email: " + c.getEmail()
                    + "| CPF: " + c.getCpf()
                    + "| Dinheiro Gasto: " + c.getDinheiroGasto());
        }
    }

    public void listarFornecedor(Sistema sistema) {

        for(Fornecedor f : sistema.cadastroFornecedor.listarTodos()) {
            System.out.println("ID: " + f.getId()
                    + "| Nome: " + f.getNome()
                    + "| Telefone" + f.getTelefone()
                    + "| Email: " + f.getEmail()
                    + "| CNPJ: " + f.getCnpj()
                    + "| Razão Social: " + f.getRazaoSocial());
        }
    }

    public void listarProduto(Sistema sistema) {
        for(Produto p : sistema.cadastroProduto.listarTodos()) {
            System.out.println("ID: " + p.getId()
                    + "| Nome: " + p.getNome()
                    + "| Descrição" + p.getDescricao()
                    + "| Preço de Custo: " + p.getPrecoCusto()
                    + "| Preço de Venda: " + p.getPrecoVenda()
                    + "| Quantidade Estoque: " + p.getQuantidadeEstoque
                    + "| Estoque Minimo: " + p.getEstoqueMinimo());
        }
    }

    public void removerCliente(Sistema sistema) {

        listarCliente(sistema);

        System.out.print("Digite o ID do cliente que deseja remover: ");
        int id = sc.nextInt();

        try {
            sistema.cadastroCliente.remover(id);
            System.out.println("Cliente removido com sucesso!");
        } catch {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void removerFornecedor(Sistema sistema) {

        listarFornecedor(sistema);

        System.out.print("Digite o ID do fornecedor que deseja remover: ");
        int id = sc.nextInt();

          try {
            sistema.cadastroFornecedor.remover(id);
            System.out.println("Fornecedor removido com sucesso!");
        } catch {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    public void removerProduto(Sistema sistema) {

        listarProduto(sistema);

        System.out.print("Digite o ID do produto que deseja remover: ");
        int id = sc.nextInt();

         try {
            sistema.cadastroProduto.remover(id);
            System.out.println("Produto removido com sucesso!");
        } catch {
            System.out.println("Produto não encontrado.");
        }
    }

    public Cliente adicionarClienteVenda(Sistema sistema) {

        for(Cliente c : sistema.listaClientes()) {
            System.out.println("ID: " + c.getId() + "Nome: " + c.getNome());
        }

        System.out.println("Digite o ID do Cliente ou 0 para continuar sem cliente: ");
        int idEscolhido = sc.nextInt();

        if(idEscolhido == 0) {
            return null;
        }

        for (Cliente c : sistema.listaClientes()) {
            if (c.getId() == idEscolhido) {
                return c;
            }
        }

        System.out.println("Cliente não encontrado");
        return null;
    }

    public void adicionarProdutoVenda(Sistema sistema) {

        Produto p = sistema.listaProdutos();
        while (true) {

            // LISTAR PRODUTOS
            for (Produto p : listaProdutos()) {
                System.out.println("ID: " + p.getId()
                        + " | Nome: " + p.getNome()
                        + " | Preço: " + p.getPrecoVenda()
                        + " | Estoque: " + p.getQuantidadeEstoque());
            }

            System.out.println("Digite o Id do produto ou (-1 Cancelar / -2 Pagamento): ");
            int produtoEscolhido = sc.nextInt();

            if (produtoEscolhido == -1) {
                sistema.alterarStatusVendaAtual(StatusVenda.CANCELADA);
                System.out.println("Venda cancelada!");
                return;
            }

            if (produtoEscolhido == -2) {

                double total = 0;

                System.out.println("\n===== RESUMO DA VENDA =====");

                for (ItemVenda item : sistema.getListaVendaAtual()) {

                    double subtotal = item.getSubtotal();
                    total += subtotal;

                    System.out.println(
                            "Produto: " + item.produto.getNome()
                                    + " | Quantidade: " + item.getQuantidade()
                                    + " | Valor Unitário: " + item.produto.getPrecoVenda()
                                    + " | Subtotal: " + subtotal
                    );
                }

                System.out.println("TOTAL DA VENDA: R$ " + total);
                System.out.println("===========================\n");

                finalizarVenda(sistema);
                return;
            }

            if (produtoSelecionado == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.println("Digite a quantidade: ");
            int quantidade = sc.nextInt();

            sistema.adicionarProduto(produtoEscolhido, quantidade);

            System.out.println("Produto adicionado ao carrinho!");
        }

    }

    public void finalizarVenda(Sistema sistema) {

        System.out.println("1 - Dinheiro | 2 - Pix | 3 - Cartão | 4 - Cancelar venda");
        int numeroEscolhido = sc.nextInt();

        if (numeroEscolhido == 4) {
            sistema.alterarStatusVendaAtual(StatusVenda.CANCELADA);
            return;
        }

        double total = sistema.getTotalVendaAtual();

        System.out.println("Total da venda: R$ " + total);

        // DESCONTO
        System.out.println("Deseja aplicar desconto? (1-Sim / 2-Não)");
        int descontoOpcao = sc.nextInt();

        if (descontoOpcao == 1) {
            System.out.println("Digite o valor do desconto: ");
            double desconto = sc.nextDouble();

            total = total - desconto;

            if (total < 0) {
                total = 0;
            }
        }

        System.out.println("Total final: R$ " + total);

        // PAGAMENTO EM DINHEIRO (CALCULAR TROCO)
        if (numeroEscolhido == 1) {

            System.out.println("Valor recebido: ");
            double valorPago = sc.nextDouble();

            if (valorPago < total) {
                System.out.println("Valor insuficiente!");
                return;
            }

            double troco = valorPago - total;

            System.out.printf("Troco: R$ %.2f\n", troco);
        }

        sistema.alterarStatusVendaAtual(StatusVenda.PAGA);

        System.out.println("Venda finalizada com sucesso!");
    }

    public void novaVenda(Sistema sistema) {

        System.out.println("ID: " + sistema.getIdProximaVenda();
        adicionarClienteVenda(sistema);
        adicionarProdutoVenda(sistema);
    }

}
