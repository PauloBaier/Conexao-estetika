package conexao;

import Config.FlyWayConfig;
import Config.HibernateConfig;
import repositories.*;
import services.*;

import static conexao.Main.*;

public class View {

    public static void menuPrincipal(
            ClienteService clienteService,
            EnderecoService enderecoService,
            FornecedorService fornecedorService,
            ProdutoService produtoService,
            VendaService vendaService,
            ContaReceberService contaReceberService,
            ContaPagarService contaPagarService,
            FinanceiroService financeiroService,
            CaixaService caixaService,
            MovimentacaoCaixaService movimentacaoCaixaService,
            RelatorioLocal relatorio,
            CategoriaService categoriaService,
            EntradaEstoqueService entradaEstoqueService
    ) {

        while (true) {
            System.out.println("\n========== SISTEMA ==========");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Vendas");
            System.out.println("3 - Estoque");
            System.out.println("4 - Financeiro");
            System.out.println("5 - Caixa");
            System.out.println("6 - Relatórios");
            System.out.println("0 - Sair");

            int opcao;

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                printEntradaInvalida();
                continue;
            }

            switch (opcao) {
                case 1 -> menuCadastros(clienteService, enderecoService, fornecedorService, produtoService, categoriaService);
                case 2 -> menuVendas(clienteService, produtoService, vendaService);
                case 3 -> menuEstoque(produtoService, fornecedorService, entradaEstoqueService);
                case 4 -> menuFinanceiro(contaReceberService, contaPagarService, financeiroService, caixaService);
                case 5 -> menuCaixa(caixaService, movimentacaoCaixaService);
                case 6 -> menuRelatorios(relatorio, produtoService);
                case 0 -> {
                    System.out.println("Encerrando sistema...");
                    HibernateConfig.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    public static void menuCadastros(
            ClienteService clienteService,
            EnderecoService enderecoService,
            FornecedorService fornecedorService,
            ProdutoService produtoService,
            CategoriaService categoriaService
    ) {
        System.out.println("\n===== CADASTROS =====");
        System.out.println("1 - Cliente");
        System.out.println("2 - Fornecedor");
        System.out.println("3 - Produto");
        System.out.println("4 - Categoria");

        System.out.println("0 - Voltar");

        int op;
        try {
            op = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            printEntradaInvalida();
            return;
        }

        switch (op) {
            case 1 -> cadastroCliente(clienteService, enderecoService);
            case 2 -> cadastroFornecedor(fornecedorService);
            case 3 -> cadastroProduto(produtoService, fornecedorService, categoriaService);
            case 4 -> cadastroCategoria(categoriaService);
            case 5 -> atualizarCliente(clienteService);
            case 6->  atualizarFornecedor(fornecedorService);
            case 7 -> atualizarProduto(produtoService, categoriaService, fornecedorService);
            case 8 -> removerCliente(clienteService);
            case 9 -> removerFornecedor(fornecedorService);
            case 10 -> relatoriotodosProdutos(produtoService);
            case 0 -> {
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void menuVendas(
            ClienteService clienteService,
            ProdutoService produtoService,
            VendaService vendaService
    ) {
        while (true) {
            System.out.println("\n===== VENDAS =====");
            System.out.println("1 - Nova venda");
            System.out.println("0 - Voltar");

            int op;

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                printEntradaInvalida();
                continue;
            }

            switch (op) {
                case 1 -> novaVenda(clienteService, produtoService, vendaService);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    public static void menuEstoque(
            ProdutoService produtoService,
            FornecedorService fornecedorService,
            EntradaEstoqueService entradaEstoqueService
    ) {
        System.out.println("\n===== ESTOQUE =====");
        System.out.println("1 - Listar produtos");
        System.out.println("2 - Entrada de produtos");
        System.out.println("0 - Voltar");

        int op;
        try {
            op = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            printEntradaInvalida();
            return;
        }

        switch (op) {
            case 1 -> relatoriotodosProdutos(produtoService);
            case 2 -> entradaDeProdutos(produtoService, fornecedorService, entradaEstoqueService);
            case 0 -> {
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void menuFinanceiro(
            ContaReceberService contaReceberService,
            ContaPagarService contaPagarService,
            FinanceiroService financeiroService,
            CaixaService caixaService
    ) {
        System.out.println("\n===== FINANCEIRO =====");
        System.out.println("1 - Contas a receber");
        System.out.println("2 - Contas a pagar");
        System.out.println("0 - Voltar");

        int op;
        try {
            op = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            printEntradaInvalida();
            return;
        }

        switch (op) {
            case 1 -> contasReceber(contaReceberService, caixaService, financeiroService);
            case 2 -> contasPagar(contaPagarService, caixaService, financeiroService);
            case 0 -> {
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void menuCaixa(
            CaixaService caixaService,
            MovimentacaoCaixaService movimentacaoCaixaService
    ) {
        System.out.println("\n===== CAIXA =====");
        System.out.println("1 - Abrir caixa");
        System.out.println("2 - Fechar caixa");
        System.out.println("3 - Movimentar caixa");
        System.out.println("4 - Ver saldo");
        System.out.println("0 - Voltar");

        int op;
        try {
            op = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            printEntradaInvalida();
            return;
        }

        switch (op) {
            case 1 -> abrirCaixa(caixaService);
            case 2 -> fecharCaixa(caixaService);
            case 3 -> movimentarCaixa(movimentacaoCaixaService, caixaService);
            case 4 -> verSaldo(movimentacaoCaixaService, caixaService);
            case 0 -> {
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void menuRelatorios(RelatorioLocal relatorio, ProdutoService produtoService) {
        System.out.println("\n===== RELATÓRIOS =====");
        System.out.println("1 - Contas a receber");
        System.out.println("2 - Contas a pagar");
        System.out.println("3 - Contas vencidas");
        System.out.println("4 - Estoque baixo");
        System.out.println("5 - Todos os produtos");
        System.out.println("0 - Voltar");

        int op;
        try {
            op = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            printEntradaInvalida();
            return;
        }

        switch (op) {
            case 1 -> relatorioContasReceber(relatorio);
            case 2 -> relatorioContasPagar(relatorio);
            case 3 -> relatorioContasVencidas(relatorio);
            case 4 -> relatorioEstoqueBaixo(relatorio);
            case 5 -> relatoriotodosProdutos(produtoService);
            case 0 -> {
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void main(String[] args) {
        FlyWayConfig.migrate();

        ClienteRepository clienteRepository = new ClienteRepository();
        EnderecoRepository enderecoRepository = new EnderecoRepository();
        FornecedorRepository fornecedorRepository = new FornecedorRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        VendaRepository vendaRepository = new VendaRepository();
        ContaReceberRepository contaReceberRepository = new ContaReceberRepository();
        ContaPagarRepository contaPagarRepository = new ContaPagarRepository();
        CaixaRepository caixaRepository = new CaixaRepository();
        MovimentacaoCaixaRepository movimentacaoCaixaRepository = new MovimentacaoCaixaRepository();
        CategoriaRepository categoriaRepository = new CategoriaRepository();
        ItemVendaRepository itemVendaRepository = new ItemVendaRepository();

        ClienteService clienteService = new ClienteService(clienteRepository);
        EnderecoService enderecoService = new EnderecoService(enderecoRepository, clienteRepository);
        FornecedorService fornecedorService = new FornecedorService(fornecedorRepository);
        ProdutoService produtoService = new ProdutoService(produtoRepository);
        CaixaService caixaService = new CaixaService(caixaRepository);
        ContaReceberService contaReceberService = new ContaReceberService(contaReceberRepository);
        ContaPagarService contaPagarService = new ContaPagarService(contaPagarRepository);
        MovimentacaoCaixaService movimentacaoCaixaService =
                new MovimentacaoCaixaService(movimentacaoCaixaRepository, caixaRepository);
        FinanceiroService financeiroService =
                new FinanceiroService(contaReceberService, contaPagarService, movimentacaoCaixaService);
        CategoriaService categoriaService = new CategoriaService(categoriaRepository);
        ItemVendaService itemVendaService =
                new ItemVendaService(itemVendaRepository, produtoService, vendaRepository);
        VendaService vendaService =
                new VendaService(
                        vendaRepository,
                        caixaService,
                        itemVendaService,
                        contaReceberService,
                        movimentacaoCaixaService
                );
        EntradaEstoqueService entradaEstoqueService =
                new EntradaEstoqueService(produtoService, contaPagarService);
        RelatorioLocal relatorio = new RelatorioLocal();

        menuPrincipal(
                clienteService,
                enderecoService,
                fornecedorService,
                produtoService,
                vendaService,
                contaReceberService,
                contaPagarService,
                financeiroService,
                caixaService,
                movimentacaoCaixaService,
                relatorio,
                categoriaService,
                entradaEstoqueService
        );
    }
}