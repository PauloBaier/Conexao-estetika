package conexao;

import Config.FlyWayConfig;
import Config.HibernateConfig;
import repositories.*;
import services.*;
import models.*;

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
            EntradaEstoqueService entradaEstoqueService,
            UsuarioService usuarioService
    ) {

        while (true) {
            System.out.println("\n========== SISTEMA ==========");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Vendas");
            System.out.println("3 - Estoque");
            System.out.println("4 - Financeiro");
            System.out.println("5 - Caixa");
            System.out.println("6 - Relatórios");
            System.out.println("7 - Listar");
            System.out.println("8 - Usuários");
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
                case 2 -> menuVendas(clienteService, produtoService, vendaService, usuarioService);
                case 3 -> menuEstoque(produtoService, fornecedorService, entradaEstoqueService);
                case 4 -> menuFinanceiro(contaReceberService, contaPagarService, financeiroService, caixaService, usuarioService);
                case 5 -> menuCaixa(caixaService, movimentacaoCaixaService, usuarioService);
                case 6 -> menuRelatorios(relatorio, produtoService);
                case 7 -> menuListar(clienteService, fornecedorService, produtoService);
                case 8 -> menuUsuarios(usuarioService);
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
        System.out.println("5 - Atualizar Cliente");
        System.out.println("6 - Atualizar Fornecedor");
        System.out.println("7 - Atualizar Produto");
        System.out.println("5 - Remover Cliente");
        System.out.println("5 - Remover Fornecedor");
        System.out.println("5 - Remover Produto");

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
            VendaService vendaService,
            UsuarioService usuarioService
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
                case 1 -> novaVenda(clienteService, produtoService, vendaService, usuarioService);
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
            CaixaService caixaService,
            UsuarioService usuarioService
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
            case 1 -> contasReceber(contaReceberService, caixaService, financeiroService, usuarioService);
            case 2 -> contasPagar(contaPagarService, caixaService, financeiroService, usuarioService);
            case 0 -> {
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void menuCaixa(
            CaixaService caixaService,
            MovimentacaoCaixaService movimentacaoCaixaService,
            UsuarioService usuarioService
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
            case 1 -> abrirCaixa(caixaService, usuarioService);
            case 2 -> fecharCaixa(caixaService, usuarioService);
            case 3 -> movimentarCaixa(movimentacaoCaixaService, caixaService, usuarioService);
            case 4 -> verSaldo(movimentacaoCaixaService, caixaService, usuarioService);
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

    public static void menuListar(
            ClienteService clienteService,
            FornecedorService fornecedorService,
            ProdutoService produtoService
    ) {
        while (true) {
            System.out.println("\n===== MENU LISTAGEM =====");
            System.out.println("1 - Listar Clientes");
            System.out.println("2 - Listar Fornecedores");
            System.out.println("3 - Listar Produtos");
            System.out.println("0 - Voltar");

            int opcao;

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                printEntradaInvalida();
                continue;
            }

            switch (opcao) {
                case 1 -> listarCliente(clienteService);
                case 2 -> listarFornecedor(fornecedorService);
                case 3 -> listarProduto(produtoService);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
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
        UsuarioRepository usuarioRepository = new UsuarioRepository();

        ClienteService clienteService = new ClienteService(clienteRepository);
        EnderecoService enderecoService = new EnderecoService(enderecoRepository, clienteRepository);
        FornecedorService fornecedorService = new FornecedorService(fornecedorRepository);
        ProdutoService produtoService = new ProdutoService(produtoRepository);
        CaixaService caixaService = new CaixaService(caixaRepository);
        ContaReceberService contaReceberService = new ContaReceberService(contaReceberRepository);
        ContaPagarService contaPagarService = new ContaPagarService(contaPagarRepository);
        MovimentacaoCaixaService movimentacaoCaixaService =new MovimentacaoCaixaService(movimentacaoCaixaRepository, caixaRepository);
        FinanceiroService financeiroService = new FinanceiroService(contaReceberService, contaPagarService, movimentacaoCaixaService);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        CategoriaService categoriaService = new CategoriaService(categoriaRepository);
        ItemVendaService itemVendaService = new ItemVendaService(itemVendaRepository, produtoService, vendaRepository);
        VendaService vendaService = new VendaService(vendaRepository, caixaService, itemVendaService, contaReceberService, movimentacaoCaixaService, usuarioService);
        EntradaEstoqueService entradaEstoqueService = new EntradaEstoqueService(produtoService, contaPagarService);
        RelatorioLocal relatorio = new RelatorioLocal();


        // Se não houver nenhum usuário cadastrado, obriga o cadastro do primeiro
        if (usuarioService.listarTodos().isEmpty()) {
            System.out.println("========================================");
            System.out.println("  Nenhum usuário encontrado no sistema.");
            System.out.println("  Cadastre o primeiro administrador.");
            System.out.println("========================================");
            cadastrarPrimeiroUsuario(usuarioService);
        }

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
                entradaEstoqueService,
                usuarioService
        );
    }
}