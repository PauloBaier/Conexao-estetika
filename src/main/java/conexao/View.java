package conexao;

import Config.FlyWayConfig;
import Config.HibernateConfig;
import model.*;
import service.*;
import repository.*;
import jakarta.persistence.*;

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
            CategoriaService categoriaService
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

                case 2 -> novaVenda(clienteService, produtoService, vendaService);

                case 3 -> menuEstoque(produtoService, fornecedorService);

                case 4 -> menuFinanceiro(contaReceberService, contaPagarService, financeiroService, caixaService, movimentacaoCaixaService);

                case 5 -> menuCaixa(caixaService, movimentacaoCaixaService);

                case 6 -> menuRelatorios(relatorio);

                case 0 -> {
                    System.out.println("Encerrando sistema...");
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
    ){

        System.out.println("\n===== CADASTROS =====");
        System.out.println("1 - Cliente");
        System.out.println("2 - Fornecedor");
        System.out.println("3 - Produto");
        System.out.printf("4 - Categoria");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> cadastroCliente(clienteService, enderecoService);
            case 2 -> cadastroFornecedor(fornecedorService);
            case 3 -> cadastroProduto(produtoService, fornecedorService, categoriaService);
            case 4 -> cadastroCategoria(categoriaService);
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

    public static void menuEstoque(ProdutoService produtoService, FornecedorService fornecedorService) {

        System.out.println("\n===== ESTOQUE =====");
        System.out.println("1 - Listar produtos");
        System.out.println("2 - Entrada de produtos");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {

            case 1 -> relatoriotodosProdutos(produtoService);

            case 2 -> entradaDeProdutos(produtoService, fornecedorService);
        }
    }

    public static void menuFinanceiro(
            ContaReceberService contaReceberService,
            ContaPagarService contaPagarService,
            FinanceiroService financeiroService,
            CaixaService caixaService,
            MovimentacaoCaixaService movimentacaoCaixaService
    ) {

        System.out.println("\n===== FINANCEIRO =====");
        System.out.println("1 - Contas a receber");
        System.out.println("2 - Contas a pagar");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {

            case 1 -> contasReceber(contaReceberService,
                    new Caixa(), // se necessário ou buscar ativo
                    financeiroService);

            case 2 -> contasPagar(contaPagarService,
                    new Caixa(),
                    financeiroService);
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

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {

            case 1 -> abrirCaixa(caixaService);

            case 2 -> {
                System.out.print("ID do caixa: ");
                Long id = Long.parseLong(sc.nextLine());
                fecharCaixa(caixaService, id);
            }

            case 3 -> {
                System.out.print("ID do caixa: ");
                Long id = Long.parseLong(sc.nextLine());
                Caixa caixa = caixaService.buscar(id);
                movimentarCaixa(movimentacaoCaixaService, caixa);
            }

            case 4 -> {
                System.out.print("ID do caixa: ");
                Long id = Long.parseLong(sc.nextLine());
                Caixa caixa = caixaService.buscar(id);
                verSaldo(movimentacaoCaixaService, caixa);
            }
        }
    }

    public static void menuRelatorios(RelatorioLocal relatorio) {

        System.out.println("\n===== RELATÓRIOS =====");
        System.out.println("1 - Contas a receber");
        System.out.println("2 - Contas a pagar");
        System.out.println("3 - Contas vencidas");
        System.out.println("4 - Estoque baixo");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {

            case 1 -> relatorioContasReceber(relatorio);

            case 2 -> relatorioContasPagar(relatorio);

            case 3 -> relatorioContasVencidas(relatorio);

            case 4 -> relatorioEstoqueBaixo(relatorio);
        }
    }

    public static void main(String[] args) {

        FlyWayConfig.migrate();

        // aqui você instancia seus services
        EntityManager em = HibernateConfig.getEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository();
        EnderecoRepository enderecoRepository = new EnderecoRepository();
        FornecedorRepository fornecedorRepository = new FornecedorRepository(em);
        ProdutoRepository produtoRepository = new ProdutoRepository(em);
        VendaRepository vendaRepository = new VendaRepository();
        ContaReceberRepository contaReceberRepository = new ContaReceberRepository();
        ContaPagarRepository contaPagarRepository = new ContaPagarRepository();
        CaixaRepository caixaRepository = new CaixaRepository();
        MovimentacaoCaixaRepository movimentacaoCaixaRepository = new MovimentacaoCaixaRepository();
        CategoriaRepository categoriaRepository = new CategoriaRepository();

        ClienteService clienteService = new ClienteService(clienteRepository);
        EnderecoService enderecoService = new EnderecoService(enderecoRepository, clienteRepository);
        FornecedorService fornecedorService = new FornecedorService(fornecedorRepository);
        ProdutoService produtoService = new ProdutoService(produtoRepository);
        VendaService vendaService = new VendaService(vendaRepository);
        ContaReceberService contaReceberService = new ContaReceberService(contaReceberRepository);
        ContaPagarService contaPagarService = new ContaPagarService(contaPagarRepository);
        CaixaService caixaService = new CaixaService(caixaRepository);
        MovimentacaoCaixaService movimentacaoCaixaService = new MovimentacaoCaixaService(movimentacaoCaixaRepository);
        FinanceiroService financeiroService = new FinanceiroService(contaReceberService, contaPagarService, movimentacaoCaixaService);
        RelatorioLocal relatorio = new RelatorioLocal();
        CategoriaService categoriaService = new CategoriaService(categoriaRepository);

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
                categoriaService
        );
    }

}
