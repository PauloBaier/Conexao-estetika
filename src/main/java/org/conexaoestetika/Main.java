package org.conexaoestetika;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class Main {

    static Sistema sistema = new Sistema();
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
                    contasReceber(sistema);
                    break;
                case 2:
                    contasPagar(sistema);
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
                    relatorioContasReceber(sistema);
                    break;
                case 2:
                    relatorioContasPagar(sistema);
                    break;
                case 3:
                    relatorioEstoqueBaixo(sistema);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // Cadastro Cliente
    public static void cadastroCliente(Sistema sistema) {

        System.out.print("ID: " + (sistema.cadastroClientes.getUltimoId() + 1));
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("CPF (11 números): ");
        String cpf = sc.nextLine();


        sistema.novoCadastroCliente(nome, telefone, email, cpf);

        System.out.println("Cliente cadastro com sucesso!");
    }

    // Cadastro Fornecedor
    public static void cadastroFornecedor(Sistema sistema) {

        System.out.print("ID: " + (sistema.cadastroFornecedores.getUltimoId() + 1));
        sc.nextLine();

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

    // Cadastro Produto
    public static void cadastroProduto(Sistema sistema) {

        System.out.print("ID: " + (sistema.cadastroProdutos.getUltimoId() + 1));
        sc.nextLine();

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
        Fornecedor fornecedor = escolherFornecedor(sistema);

        sistema.novoCadastroProduto(nome, descricao, precoCusto, precoVenda, qntdEstoque, estoqueMinimo, fornecedor);

        System.out.println("Pproduto cadastrado com sucesso!");

    }

    // Loop que exibe todos os fornecedores e escolhe por id
    public static Fornecedor escolherFornecedor(Sistema sistema) {

        for (Fornecedor f : sistema.cadastroFornecedores.listarTodos()) {
            System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome());
        }

        System.out.print("Digite o ID do fornecedor: ");
        int idEscolhido = sc.nextInt();

        for (Fornecedor f : sistema.cadastroFornecedores.listarTodos()) {
            if (f.getId() == idEscolhido) {
                return f;
            }
        }

        System.out.println("Fornecedor não encontrado.");
        return null;
    }

    // Função para listar todos os clientes cadastrados
    public static void listarCliente(Sistema sistema) {

        for(Cliente c : sistema.cadastroClientes.listarTodos()) {
            System.out.println("ID: " + c.getId()
                    + "| Nome: " + c.getNome()
                    + "| Telefone" + c.getTelefone()
                    + "| Email: " + c.getEmail()
                    + "| CPF: " + c.getCpf()
                    + "| Dinheiro Gasto: " + c.getDinheiroGasto());
        }
    }

    // Função para listar todos os fornecedores cadastrados
    public static void listarFornecedor(Sistema sistema) {

        for(Fornecedor f : sistema.cadastroFornecedores.listarTodos()) {
            System.out.println("ID: " + f.getId()
                    + "| Nome: " + f.getNome()
                    + "| Telefone" + f.getTelefone()
                    + "| Email: " + f.getEmail()
                    + "| CNPJ: " + f.getCnpj()
                    + "| Razão Social: " + f.getRazaoSocial());
        }
    }

    // Função para listar todos os produtos cadastrados
    public static void listarProduto(Sistema sistema) {
        for(Produto p : sistema.cadastroProdutos.listarTodos()) {
            System.out.println("ID: " + p.getId()
                    + "| Nome: " + p.getNome()
                    + "| Preço de Custo: " + p.getPrecoCusto()
                    + "| Preço de Venda: " + p.getPrecoVenda()
                    + "| Quantidade Estoque: " + p.getQuantidadeEstoque()
                    + "| Estoque Minimo: " + p.getEstoqueMinimo());
        }
    }

    // Função para remover cliente por id
    public static void removerCliente(Sistema sistema) {

        listarCliente(sistema);

        System.out.print("Digite o ID do cliente que deseja remover: ");
        int id = sc.nextInt();

        try {
            sistema.cadastroClientes.remover(id);
            System.out.println("Cliente removido com sucesso!");
        } catch (Exception ex){
            System.out.println("Cliente não encontrado.");
        }
    }

    // Função para remover fornecedor por id
    public static void removerFornecedor(Sistema sistema) {

        listarFornecedor(sistema);

        System.out.print("Digite o ID do fornecedor que deseja remover: ");
        int id = sc.nextInt();

        try {
            sistema.cadastroFornecedores.remover(id);
            System.out.println("Fornecedor removido com sucesso!");
        } catch(Exception ex){
            System.out.println("Fornecedor não encontrado.");
        }
    }

    // Função para remover produtos por id
    public static void removerProduto(Sistema sistema) {

        listarProduto(sistema);

        System.out.print("Digite o ID do produto que deseja remover: ");
        int id = sc.nextInt();

        try {
            sistema.cadastroProdutos.remover(id);
            System.out.println("Produto removido com sucesso!");
        } catch(Exception ex){
            System.out.println("Produto não encontrado.");
        }
    }

    // Função para adicionar cliente a venda(caso tenha)
    public static Cliente adicionarClienteVenda(Sistema sistema) {

        for(Cliente c : sistema.cadastroClientes.listarTodos()) {
            System.out.println("ID: " + c.getId() + "Nome: " + c.getNome());
        }

        System.out.println("Digite o ID do Cliente ou 0 para continuar sem cliente: ");
        int idEscolhido = sc.nextInt();

        if(idEscolhido == 0) {
            return null;
        }

        for (Cliente c : sistema.cadastroClientes.listarTodos()) {
            if (c.getId() == idEscolhido) {
                return c;
            }
        }

        System.out.println("Cliente não encontrado");
        return null;
    }

    // Função para adicionar produto a venda e exibir como um carrinho
    public static void adicionarProdutoVenda(Sistema sistema) {

        while (true) {

            // LISTAR PRODUTOS
            for (Produto p : sistema.cadastroProdutos.listarTodos()) {
                System.out.println("ID: " + p.getId()
                        + " | Nome: " + p.getNome()
                        + " | Preço: " + p.getPrecoVenda()
                        + " | Estoque: " + p.getQuantidadeEstoque());
            }

            System.out.println("Digite o Id do produto ou (-1 Cancelar / -2 Pagamento): ");
            int produtoEscolhido = sc.nextInt();

            if (produtoEscolhido == -1) {
                sistema.alterarStatusVendaAtual(StatusVenda.CANCELADO);
                sistema.registrarVendaAtual();
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
                            "Produto: " + item.getProduto().getNome()
                                    + " | Quantidade: " + item.getQuantidade()
                                    + " | Valor Unitário: " + item.getProduto().getPrecoVenda()
                                    + " | Subtotal: " + subtotal
                    );
                }

                System.out.println("TOTAL DA VENDA: R$ " + total);
                System.out.println("===========================\n");

                finalizarVenda(sistema);
                return;
            }

            Produto produtoSelecionado = sistema.cadastroProdutos.buscarId(produtoEscolhido);

            if (produtoSelecionado == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.println("Digite a quantidade: ");
            int quantidade = sc.nextInt();

            sistema.adicionarProdutoVenda(produtoSelecionado, quantidade);

            System.out.println("Produto adicionado ao carrinho!");
        }

    }

    // Função para finalizar a venda e registar qual método de pagamento foi utilizado e registar status da venda
    public static void finalizarVenda(Sistema sistema) {

        System.out.println("1 - Dinheiro | 2 - Pix | 3 - Cartão | 4 - Cancelar venda");
        int numeroEscolhido = sc.nextInt();

        if (numeroEscolhido == 4) {
            sistema.alterarStatusVendaAtual(StatusVenda.CANCELADO);
            sistema.registrarVendaAtual();
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

        sistema.alterarStatusVendaAtual(StatusVenda.PAGO);
        sistema.registrarVendaAtual();

        System.out.println("Venda finalizada com sucesso!");
    }

    // Função para agrupar todas as funções que geram a venda
    public static void novaVenda(Sistema sistema) {

        System.out.println("ID: " + sistema.getIdProximaVenda());
        sistema.novaVenda(adicionarClienteVenda(sistema));
        adicionarProdutoVenda(sistema);
    }

    // Função para exibir todas as vendas que não foram fechadas
    public static void contasReceber(Sistema sistema) {

        System.out.println("\n===== CONTAS A RECEBER =====");

        for (ContaReceber c : sistema.listaTodasContasReceber(false)) {

            System.out.println(
                    "ID: " + c.getId()
                            + " | Cliente: " + c.getCliente().getNome()
                            + " | Valor: R$ " + c.getValor()
                            + " | Data: " + c.getData()
            );
        }

        System.out.print("Digite o ID da conta para marcar como paga (0 para voltar): ");
        int id = sc.nextInt();

        if (id != 0) {

            if (sistema.receberConta(id)) {
                System.out.println("Conta marcada como paga!");
            } else {
                System.out.println("Erro ao marcar conta.");
            }
        }
    }

    // Funçãp para exibir todas as contas que não foram pagas e precisam ser fechadas e pagas
    public static void contasPagar(Sistema sistema) {

        System.out.println("\n===== CONTAS A PAGAR =====");

        for (ContaPagar c : sistema.listaTodasContasPagar(false)) {

            System.out.println(
                    "ID: " + c.getId()
                            + " | Fornecedor: " + c.getFornecedor().getNome()
                            + " | Valor: R$ " + c.getValor()
                            + " | Vencimento: " + c.getVencimento()
            );
        }

        System.out.print("Digite o ID da conta para pagar (0 para voltar): ");
        int id = sc.nextInt();

        if (id != 0) {

            if (sistema.PagarConta(id)) {
                System.out.println("Conta paga com sucesso!");
            } else {
                System.out.println("Erro ao pagar conta.");
            }
        }
    }

    // Função para filtrar o relatório
    public static StatusVenda escolherFiltroStatus() {

        System.out.println("Filtro de Status:");
        System.out.println("1 - Todos");
        System.out.println("2 - Pagos");
        System.out.println("3 - Pendentes");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 2:
                return StatusVenda.PAGO;

            case 3:
                return StatusVenda.PENDENTE;

            default:
                return StatusVenda.CANCELADO;
        }
    }

    // Função para gerar relatório de contas a receber
    public static void relatorioContasReceber(Sistema sistema) {

        System.out.println("\n===== RELATÓRIO CONTAS A RECEBER =====");

        System.out.print("Data inicial (AAAA-MM-DD): ");
        LocalDate dataInicial = LocalDate.parse(sc.next());

        System.out.print("Data final (AAAA-MM-DD): ");
        LocalDate dataFinal = LocalDate.parse(sc.next());

        StatusVenda filtro = escolherFiltroStatus();

        List<ContaReceber> lista = sistema.listarContasReceber(dataInicial, dataFinal, filtro);

        for (ContaReceber c : lista) {

            System.out.println(
                    "ID: " + c.getId()
                            + " | Cliente: " + c.getCliente().getNome()
                            + " | Valor: R$ " + c.getValor()
                            + " | Data: " + c.getData()
                            + " | Pago: " + c.estaPago()
            );
        }
    }

    // Função para gerar relatório de contas a pagar
    public static void relatorioContasPagar(Sistema sistema) {

        System.out.println("\n===== RELATÓRIO CONTAS A PAGAR =====");

        System.out.print("Data inicial (AAAA-MM-DD): ");
        LocalDate dataInicial = LocalDate.parse(sc.next());

        System.out.print("Data final (AAAA-MM-DD): ");
        LocalDate dataFinal = LocalDate.parse(sc.next());

        StatusVenda filtro = escolherFiltroStatus();

        List<ContaPagar> lista = sistema.listarContasPagar(dataInicial, dataFinal, filtro);

        for (ContaPagar c : lista) {

            System.out.println(
                    "ID: " + c.getId()
                            + " | Fornecedor: " + c.getFornecedor().getNome()
                            + " | Valor: R$ " + c.getValor()
                            + " | Vencimento: " + c.getVencimento()
                            + " | Pago: " + c.estaPago()
            );
        }
    }

    // Função para gerar relatório de estoque baixo
    public static void relatorioEstoqueBaixo(Sistema sistema) {

        System.out.println("\n===== PRODUTOS COM ESTOQUE BAIXO =====");

        for(Produto p : sistema.listarProdutosEstoqueAbaixoMin()) {

            System.out.println(
                    "Produto: " + p.getNome()
                            + " | Estoque: " + p.getQuantidadeEstoque()
                            + " | Mínimo: " + p.getEstoqueMinimo()
            );
        }
    }

}
