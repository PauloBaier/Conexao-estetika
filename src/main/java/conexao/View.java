package conexao;

import Config.FlyWayConfig;
import Config.HibernateConfig;
import repositories.*;
import services.*;
import models.*;
import models.enums.*;

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
            UsuarioService usuarioService,
            Usuario usuarioLogado
    ) {
        // variável local mutável para permitir troca de usuário na sessão
        Usuario usuarioAtual = usuarioLogado;

        while (true) {
            TipoUsuario perfil = usuarioAtual.getPerfil();

            System.out.println("\n========== SISTEMA ==========");
            System.out.println("Logado como: " + usuarioAtual.getNome() + " [" + perfil + "]");
            System.out.println("------------------------------");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Vendas");
            System.out.println("3 - Estoque");
            System.out.println("4 - Relatórios");
            System.out.println("5 - Listar");
            System.out.println("6 - Financeiro");
            System.out.println("7 - Caixa");
            System.out.println("8 - Usuários");
            System.out.println("9 - Trocar usuário");
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
                case 2 -> menuVendas(clienteService, produtoService, vendaService, usuarioAtual);
                case 3 -> menuEstoque(produtoService, fornecedorService, entradaEstoqueService);
                case 4 -> menuRelatorios(relatorio, produtoService);
                case 5 -> menuListar(clienteService, fornecedorService, produtoService);
                case 6 -> menuFinanceiro(contaReceberService, contaPagarService, financeiroService, caixaService, usuarioAtual, usuarioService);
                case 7 -> menuCaixa(caixaService, movimentacaoCaixaService, usuarioAtual, usuarioService);
                case 8 -> menuUsuarios(usuarioService, usuarioAtual);
                case 9 -> {
                    Usuario novoUsuario = fazerLogin(usuarioService);
                    if (novoUsuario != null) {
                        usuarioAtual = novoUsuario;
                        System.out.println("Usuário alterado com sucesso!");
                    } else {
                        System.out.println("Login falhou. Usuário anterior mantido.");
                    }
                }
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
            Usuario usuarioLogado
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
                case 1 -> novaVenda(clienteService, produtoService, vendaService, usuarioLogado);
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
            Usuario usuarioLogado,
            UsuarioService usuarioService
    ) {
        // Se for FUNCIONARIO, exige autenticação de GERENTE ou ADMINISTRADOR
        Usuario usuarioOp = usuarioLogado;
        TipoUsuario perfil = usuarioLogado.getPerfil();
        if (perfil == TipoUsuario.FUNCIONARIO) {
            System.out.println("Acesso restrito. Autentique com GERENTE ou ADMINISTRADOR:");
            usuarioOp = autenticarGerente(usuarioService);
            if (usuarioOp == null) return;
        }

        final Usuario usuarioFinal = usuarioOp;

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
            case 1 -> contasReceber(contaReceberService, caixaService, financeiroService, usuarioFinal);
            case 2 -> contasPagar(contaPagarService, caixaService, financeiroService, usuarioFinal);
            case 0 -> {
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    public static void menuCaixa(
            CaixaService caixaService,
            MovimentacaoCaixaService movimentacaoCaixaService,
            Usuario usuarioLogado,
            UsuarioService usuarioService
    ) {
        // Se for FUNCIONARIO, exige autenticação de GERENTE ou ADMINISTRADOR
        Usuario usuarioOp = usuarioLogado;
        TipoUsuario perfil = usuarioLogado.getPerfil();
        if (perfil == TipoUsuario.FUNCIONARIO) {
            System.out.println("Acesso restrito. Autentique com GERENTE ou ADMINISTRADOR:");
            usuarioOp = autenticarGerente(usuarioService);
            if (usuarioOp == null) return;
        }

        final Usuario usuarioFinal = usuarioOp;

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
            case 1 -> abrirCaixa(caixaService, usuarioFinal);
            case 2 -> fecharCaixa(caixaService, usuarioFinal);
            case 3 -> movimentarCaixa(movimentacaoCaixaService, caixaService, usuarioFinal);
            case 4 -> verSaldo(movimentacaoCaixaService, caixaService, usuarioFinal);
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
            System.out.println("\n========================================");
            System.out.println("  Nenhum usuário encontrado no sistema.");
            System.out.println("  Cadastre o primeiro administrador.");
            System.out.println("========================================");
            cadastrarPrimeiroUsuario(usuarioService);
        }

        // Login único de sessão
        Usuario usuarioLogado = fazerLogin(usuarioService);
        if (usuarioLogado == null) {
            System.out.println("Sistema encerrado.");
            HibernateConfig.close();
            return;
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
                usuarioService,
                usuarioLogado
        );
    }
}