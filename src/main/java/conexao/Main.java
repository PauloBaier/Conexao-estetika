package conexao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import Config.FlyWayConfig;
import model.*;
import model.enums.FormaPagamento;
import model.enums.StatusConta;
import model.enums.StatusVenda;
import model.enums.TipoMovimento;
import service.*;
import repository.*;
import jakarta.persistence.*;

public class Main {


    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        FlyWayConfig.migrate();
    }

    // Cadastro Cliente
    public static void cadastroCliente(ClienteService clienteService, EnderecoService enderecoService) {

            Cliente cliente = new Cliente();

            System.out.print("Nome: ");
            cliente.setNome(sc.nextLine());

            System.out.print("Telefone: ");
            cliente.setTelefone(sc.nextLine());

            System.out.print("Email: ");
            cliente.setEmail(sc.nextLine());

            System.out.print("CPF (11 números): ");
            cliente.setCpf(sc.nextLine());

            clienteService.cadastrar(cliente);

            if (cliente.getId() == null || cliente.getId() == 0) {
                throw new RuntimeException("Erro ao salvar cliente");
            }

            System.out.println("\n=== Endereço ===");

            cadastroEndereco(enderecoService, cliente.getId());

            System.out.println("Cliente cadastrado com sucesso!");


    }

    // Cadastro Endereco
    public static void cadastroEndereco(EnderecoService enderecoService, Long clienteId) {


            Endereco endereco = new Endereco();

            System.out.print("Rua: ");
            endereco.setRua(sc.nextLine());

            System.out.print("Bairro: ");
            endereco.setBairro(sc.nextLine());

            System.out.print("Número: ");
            endereco.setNumero(sc.nextLine());

            System.out.print("CEP: ");
            endereco.setCep(sc.nextLine());


            enderecoService.cadastrarEndereco(endereco, clienteId);

    }

    // Cadastro Fornecedor
    public static void cadastroFornecedor(FornecedorService fornecedorService) {

        System.out.println("=== NOVO FORNECEDOR ===");

        Fornecedor fornecedor = new Fornecedor();


            System.out.print("Nome: ");
            fornecedor.setNome(sc.nextLine());

            System.out.print("Telefone: ");
            fornecedor.setTelefone(sc.nextLine());

            System.out.print("Email: ");
            fornecedor.setEmail(sc.nextLine());

            System.out.print("CNPJ (14 números): ");
            fornecedor.setCnpj(sc.nextLine());

            System.out.print("Razão Social: ");
            fornecedor.setRazaoSocial(sc.nextLine());


            fornecedorService.salvar(fornecedor);

            System.out.println("Fornecedor cadastrado com sucesso!");

    }

    public static void cadastroCategoria(CategoriaService categoriaService) {

        try {
            Categoria categoria = new Categoria();

            System.out.print("Nome da categoria: ");
            categoria.setNome(sc.nextLine());


            categoriaService.cadastrar(categoria);

            System.out.println("Categoria cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }



    // Cadastro Produto
    public static void cadastroProduto(
            ProdutoService produtoService,
            FornecedorService fornecedorService,
            CategoriaService categoriaService
    ) {

        System.out.println("=== NOVO PRODUTO ===");
        Produto produto = new Produto();

        try {
            System.out.print("Nome: ");
            produto.setNome(sc.nextLine());

            System.out.print("Preço de Custo: ");
            produto.setPrecoCompra(Double.parseDouble(sc.nextLine()));

            System.out.print("Preço de Venda: ");
            produto.setPrecoVenda(Double.parseDouble(sc.nextLine()));

            System.out.print("Quantidade de Estoque: ");
            produto.setQuantidadeEstoque(Integer.parseInt(sc.nextLine()));

            System.out.print("Estoque Mínimo: ");
            produto.setEstoqueMinimo(Integer.parseInt(sc.nextLine()));

            System.out.println("\n=== Escolha Categoria ===");
            Categoria categoria = escolherCategoria(categoriaService);

            if (categoria == null) {
                System.out.println("Categoria obrigatória!");
                return;
            }

            produto.setCategoria(categoria);

            System.out.println("\n=== Escolha Fornecedor ===");
            Fornecedor fornecedor = escolherFornecedor(fornecedorService);

            if (fornecedor == null) {
                System.out.println("Fornecedor obrigatório!");
                return;
            }

            produto.adicionarFornecedor(fornecedor);

            produtoService.salvar(produto);

            System.out.println("Produto cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public static Categoria escolherCategoria(CategoriaService categoriaService) {

        List<Categoria> categorias = categoriaService.listarTodas();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada!");
            return null;
        }

        System.out.println("\n===== LISTA DE CATEGORIAS =====");

        for (Categoria c : categorias) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        while (true) {
            try {
                System.out.print("Digite o ID da categoria: ");
                long id = Long.parseLong(sc.nextLine());

                Categoria categoria = categoriaService.buscarPorId(id);

                if (categoria == null) {
                    System.out.println("Categoria não encontrada!");
                    continue;
                }

                return categoria;

            } catch (Exception e) {
                printEntradaInvalida();
            }
        }
    }

    // Loop que exibe todos os fornecedores e escolhe por id
    public static Fornecedor escolherFornecedor(FornecedorService fornecedorService) {

        List<Fornecedor> fornecedores = fornecedorService.listarTodos();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado!");
            return null;
        }

        for (Fornecedor f : fornecedores) {
            System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome());
        }

        System.out.print("Digite o ID do fornecedor: ");
        long id = sc.nextLong();
        sc.nextLine(); // limpar buffer

        Fornecedor fornecedor = fornecedorService.buscarPorId(id);

        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return null;
        }

        return fornecedor;
    }

    // Função para listar todos os clientes cadastrados
    public static void listarCliente(ClienteService clienteService) {

        List<Cliente> clientes = clienteService.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        for (Cliente c : clientes) {
            System.out.println(
                    "ID: " + c.getId() +
                            " | Nome: " + c.getNome() +
                            " | Telefone: " + c.getTelefone() +
                            " | Email: " + c.getEmail() +
                            " | CPF: " + c.getCpf()
            );
        }
    }

    // Função para listar todos os fornecedores cadastrados
    public static void listarFornecedor(FornecedorService fornecedorService) {

        List<Fornecedor> fornecedores = fornecedorService.listarTodos();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado!");
            return;
        }

        System.out.println("\n===== LISTA DE FORNECEDORES =====");

        for (Fornecedor f : fornecedores) {
            System.out.println(
                    "ID: " + f.getId()
                            + " | Nome: " + f.getNome()
                            + " | Telefone: " + f.getTelefone()
                            + " | Email: " + f.getEmail()
                            + " | CNPJ: " + f.getCnpj()
                            + " | Razão Social: " + f.getRazaoSocial()
            );
        }
    }

    // Função para listar todos os produtos cadastrados
    public static void listarProduto(ProdutoService produtoService) {

        List<Produto> produtos = produtoService.listarTodos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
            return;
        }

        System.out.println("\n===== LISTA DE PRODUTOS =====");

        for (Produto p : produtos) {

            System.out.println(
                    "ID: " + p.getId()
                            + " | Nome: " + p.getNome()
                            + " | Preço de Compra: " + p.getPrecoCompra()
                            + " | Preço de Venda: " + p.getPrecoVenda()
                            + " | Estoque: " + p.getQuantidadeEstoque()
                            + " | Estoque Mínimo: " + p.getEstoqueMinimo()
            );
        }
    }

    // Função para remover cliente por id
    public static void removerCliente(ClienteService clienteService) {

        listarCliente(clienteService);

        try {
            System.out.print("Digite o ID do cliente que deseja remover: ");
            Long id = Long.parseLong(sc.nextLine());

            if (id <= 0) {
                System.out.println("ID inválido!");
                return;
            }

            clienteService.deletar(id);

            System.out.println("Cliente removido com sucesso!");

        } catch (NumberFormatException ex) {
            System.out.println("ID inválido! Digite apenas números.");
        } catch (Exception ex) {
            System.out.println("Erro ao remover cliente: " + ex.getMessage());
        }
    }

    // Função para remover fornecedor por id
    public static void removerFornecedor(FornecedorService fornecedorService) {

        listarFornecedor(fornecedorService);

        try {
            System.out.print("Digite o ID do fornecedor que deseja remover: ");
            long id = Long.parseLong(sc.nextLine());

            if (id <= 0) {
                System.out.println("ID inválido!");
                return;
            }

            Fornecedor fornecedor = fornecedorService.buscarPorId(id);

            if (fornecedor == null) {
                System.out.println("Fornecedor não encontrado!");
                return;
            }

            fornecedorService.delete(fornecedor);

            System.out.println("Fornecedor removido com sucesso!");

        } catch (NumberFormatException ex) {
            System.out.println("ID inválido! Digite apenas números.");
        } catch (Exception ex) {
            System.out.println("Erro ao remover fornecedor: " + ex.getMessage());
        }
    }

    // Função para remover produtos por id
    public static void removerProduto(ProdutoService produtoService) {

        listarProduto(produtoService);

        try {
            System.out.print("Digite o ID do produto que deseja remover: ");
            long id = Long.parseLong(sc.nextLine());

            if (id <= 0) {
                System.out.println("ID inválido!");
                return;
            }

            Produto produto = produtoService.buscarPorId(id);

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                return;
            }

            produtoService.delete(produto);

            System.out.println("Produto removido com sucesso!");

        } catch (NumberFormatException ex) {
            System.out.println("ID inválido! Digite apenas números.");
        } catch (Exception ex) {
            System.out.println("Erro ao remover produto: " + ex.getMessage());
        }
    }

    // Função para adicionar cliente a venda(caso tenha)
    public static Cliente adicionarClienteVenda(ClienteService clienteService) {

        List<Cliente> clientes = clienteService.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Venda sem cliente.");
            return null;
        }

        for (Cliente c : clientes) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        long idEscolhido = -1;

        while (true) {
            try {
                System.out.println("Digite o ID do cliente (0 para continuar sem cliente): ");
                idEscolhido = Long.parseLong(sc.nextLine());

                if (idEscolhido < 0) {
                    System.out.println("ID inválido!");
                    continue;
                }

                break;

            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida! Digite apenas números.");
            }
        }

        if (idEscolhido == 0) {
            return null;
        }

        for (Cliente c : clientes) {
            if (c.getId() == idEscolhido) {
                return c;
            }
        }

        System.out.println("Cliente não encontrado. Venda sem cliente.");
        return null;
    }

    // Função para adicionar produto a venda e exibir como um carrinho
    public static void adicionarProdutoVenda(Venda venda, ProdutoService produtoService) {

        while (true) {

            List<Produto> produtos = produtoService.listarTodos();

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado!");
                return;
            }

            System.out.println("\n===== PRODUTOS =====");

            for (Produto p : produtos) {
                System.out.println(
                        "ID: " + p.getId()
                                + " | Nome: " + p.getNome()
                                + " | Preço: " + p.getPrecoVenda()
                                + " | Estoque: " + p.getQuantidadeEstoque()
                );
            }

            System.out.println("\nDigite o ID do produto (-1 cancelar | -2 pagamento): ");

            long id;

            try {
                id = Long.parseLong(sc.nextLine());
            } catch (Exception ex) {
                printEntradaInvalida();
                continue;
            }

            // CANCELAR
            if (id == -1) {
                venda.setStatus(StatusVenda.CANCELADO);
                System.out.println("Venda cancelada!");
                return;
            }

            // PAGAMENTO
            if (id == -2) {

                if (venda.getItens() == null || venda.getItens().isEmpty()) {
                    System.out.println("Venda vazia!");
                    venda.setStatus(StatusVenda.CANCELADO);
                    return;
                }

                System.out.println("\n===== RESUMO DA VENDA =====");

                for (ItemVenda item : venda.getItens()) {
                    System.out.println(
                            "Produto: " + item.getProduto().getNome()
                                    + " | Qtd: " + item.getQuantidade()
                                    + " | Unit: " + item.getPrecoUnitario()
                                    + " | Subtotal: " + item.getTotalItem()
                    );
                }

                System.out.println("TOTAL: R$ " + venda.getValorTotal());
                System.out.println("===========================\n");

                finalizarVenda(venda);
                return;
            }

            Produto produto = produtoService.buscarPorId(id);

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.println("Digite a quantidade: ");

            int quantidade;

            try {
                quantidade = Integer.parseInt(sc.nextLine());
            } catch (Exception ex) {
                printEntradaInvalida();
                continue;
            }

            if (quantidade <= 0) {
                System.out.println("Quantidade inválida!");
                continue;
            }

            if (produto.getQuantidadeEstoque() < quantidade) {
                System.out.println("Estoque insuficiente!");
                continue;
            }

            venda.adicionarItem(produto, quantidade);

            System.out.println("Produto adicionado ao carrinho!");
        }
    }

    public static void finalizarVenda(Venda venda) {

        if (venda == null) {
            System.out.println("Venda inválida!");
            return;
        }

        while (true) {

            System.out.println("1 - Dinheiro | 2 - Pix | 3 - Cartão | 4 - Cancelar");

            int opcao;

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception ex) {
                printEntradaInvalida();
                continue;
            }

            switch (opcao) {

                case 1 -> venda.setFormaPagamento(FormaPagamento.DINHEIRO);
                case 2 -> venda.setFormaPagamento(FormaPagamento.PIX);
                case 3 -> venda.setFormaPagamento(FormaPagamento.CARTAO);

                case 4 -> {
                    venda.setStatus(StatusVenda.CANCELADO);
                    System.out.println("Venda cancelada!");
                    return;
                }

                default -> {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            venda.setStatus(StatusVenda.PAGO);

            System.out.println("Venda finalizada com sucesso!");
            break;
        }
    }

    public static boolean pagamentoDinheiro(Venda venda) {

        int descontoOpcao;
        double valorFinal = venda.getValorTotal();

        // DESCONTO
        while (true) {

            System.out.println("Total da venda: R$ " + venda.getValorTotal());
            System.out.println("Deseja aplicar desconto? (1-Sim / 2-Não)");

            try {
                descontoOpcao = Integer.parseInt(sc.nextLine());
            } catch (Exception ex) {
                printEntradaInvalida();
                continue;
            }

            if (descontoOpcao == 1) {

                System.out.println("Digite o desconto (%): ");

                try {
                    double desconto = Double.parseDouble(sc.nextLine());

                    if (desconto < 0 || desconto > 100) {
                        System.out.println("Desconto inválido!");
                        continue;
                    }

                    valorFinal = venda.getValorTotal() - (venda.getValorTotal() * desconto / 100);

                } catch (Exception ex) {
                    printEntradaInvalida();
                    continue;
                }

                break;

            } else if (descontoOpcao == 2) {
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }

        System.out.printf("Total final: R$ %.2f\n", valorFinal);

        // PAGAMENTO
        double valorPago;
        double troco;

        while (true) {

            System.out.println("Valor recebido (-1 para cancelar): ");

            try {
                valorPago = Double.parseDouble(sc.nextLine());
            } catch (Exception ex) {
                printEntradaInvalida();
                continue;
            }

            if (valorPago == -1) return false;

            if (valorPago < valorFinal) {
                System.out.println("Valor insuficiente!");
                continue;
            }

            troco = valorPago - valorFinal;
            break;
        }

        System.out.printf("Troco: R$ %.2f\n", troco);

        return true;
    }

    public static void novaVenda(ClienteService clienteService, ProdutoService produtoService, VendaService vendaService) {

        System.out.println("=== NOVA VENDA ===");

        Cliente cliente = adicionarClienteVenda(clienteService);

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setData(LocalDate.now());
        venda.setStatus(StatusVenda.PENDENTE);
        venda.setValorTotal(0);

        adicionarProdutoVenda(venda, produtoService);

        if (venda.getStatus() == StatusVenda.CANCELADO) {
            System.out.println("Venda cancelada.");
            return;
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            System.out.println("Venda sem itens. Operação cancelada.");
            return;
        }

        vendaService.cadastrar(venda);

        System.out.println("Venda criada com sucesso! ID: " + venda.getId());
    }

    // Função para exibir todas as vendas que não foram fechadas
    public static void contasReceber(ContaReceberService contaReceberService, Caixa caixa, FinanceiroService financeiroService) {

        System.out.println("\n===== CONTAS A RECEBER =====");

        while (true) {

            try {

                List<ContaReceber> contas = contaReceberService.listar();

                for (ContaReceber c : contas) {
                    System.out.println(
                            "ID: " + c.getId()
                                    + " | Cliente: " + (c.getCliente() == null
                                    ? "Venda sem cliente"
                                    : c.getCliente().getNome())
                                    + " | Valor: R$ " + c.getValor()
                                    + " | Emissão: " + c.getDataEmissao()
                                    + " | Vencimento: " + c.getDataVencimento()
                                    + " | Pagamento: " + c.getDataPagamento()
                                    + " | Status: " + c.getStatus()
                    );
                }

                System.out.print("Digite o ID da conta para marcar como paga (0 para voltar): ");
                Long id = Long.parseLong(sc.nextLine());

                if (id == 0){
                    return;
                }

                ContaReceber conta = contaReceberService.buscar(id);

                if (conta == null) {
                    System.out.println("Conta não encontrada!");
                    continue;
                }

                financeiroService.receberConta(conta, caixa);

                System.out.println("Conta marcada como paga!");

                break;

            } catch (NumberFormatException ex) {
                printEntradaInvalida();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Funçãp para exibir todas as contas que não foram pagas e precisam ser fechadas e pagas
    public static void contasPagar(ContaPagarService contaPagarService, Caixa caixa, FinanceiroService financeiroService) {

        System.out.println("\n===== CONTAS A PAGAR =====");

        while (true) {

            try {

                List<ContaPagar> contas = contaPagarService.listar();

                for (ContaPagar c : contas) {
                    System.out.println(
                            "ID: " + c.getId()
                                    + " | Fornecedor: " + c.getFornecedor().getNome()
                                    + " | Valor: R$ " + c.getValor()
                                    + " | Vencimento: " + c.getDataVencimento()
                                    + " | Status: " + c.getStatus()
                    );
                }

                System.out.print("Digite o ID da conta para pagar (0 para voltar): ");
                Long id = Long.parseLong(sc.nextLine());

                if (id == 0) {
                    return;
                }

                ContaPagar conta = contaPagarService.buscar(id);

                if (conta == null) {
                    System.out.println("Conta não encontrada!");
                    continue;
                }

                financeiroService.pagarConta(conta, caixa);

                System.out.println("Conta paga com sucesso!");

                break;

            } catch (NumberFormatException ex) {
                printEntradaInvalida();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void entradaDeProdutos(ProdutoService produtoService, FornecedorService fornecedorService) {

        double valor = 0;

        List<ItemVenda> itens = adicionarProdutosEntrada(produtoService);

        if (itens.isEmpty()) return;

        Fornecedor fornecedor = adicionarFornecedorEntrada(fornecedorService);

        if (fornecedor == null) return;

        LocalDate vencimento;

        while (true) {
            try {
                System.out.println("Digite a Data de Vencimento (AAAA-MM-DD) (-1 Cancelar)");
                String entrada = sc.nextLine();

                if (entrada.equals("-1")) return;

                vencimento = LocalDate.parse(entrada);
                break;

            } catch (Exception ex) {
                printEntradaInvalida();
            }
        }

        for (ItemVenda item : itens) {
            valor += item.getTotalItem();
        }

        EntradaEstoqueService.registrarEntradaEstoque(itens, fornecedor, valor, vencimento);
    }

    public static List<ItemVenda> adicionarProdutosEntrada(ProdutoService produtoService) {

        List<ItemVenda> produtos = new ArrayList<>();

        while (true) {

            Produto produto;
            int quantidade;

            listarProduto(produtoService);

            System.out.println("Digite o ID do Produto (-1 continuar / -2 cancelar)");

            int opcao;

            try {
                opcao = Integer.parseInt(sc.nextLine());

                if (opcao == -1) return produtos;

                if (opcao == -2) return new ArrayList<>();

                produto = produtoService.buscarPorId(opcao);

                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                    continue;
                }

                System.out.println("Digite a quantidade:");

                quantidade = Integer.parseInt(sc.nextLine());

                if (quantidade <= 0) {
                    System.out.println("Quantidade inválida!");
                    continue;
                }

                ItemVenda item = new ItemVenda();
                item.setProduto(produto);
                item.setQuantidade(quantidade);
                item.setPrecoUnitario(produto.getPrecoCompra());
                item.setTotalItem(produto.getPrecoCompra() * quantidade);

                produtos.add(item);

            } catch (Exception ex) {
                printEntradaInvalida();
            }
        }
    }

    public static Fornecedor adicionarFornecedorEntrada(FornecedorService fornecedorService) {

        while (true) {

            listarFornecedor(fornecedorService);

            try {
                System.out.println("Digite o ID do Fornecedor (-1 cancelar)");
                int opcao = Integer.parseInt(sc.nextLine());

                if (opcao == -1) return null;

                Fornecedor fornecedor = fornecedorService.buscarPorId(opcao);

                if (fornecedor == null) {
                    System.out.println("Fornecedor não encontrado!");
                    continue;
                }

                return fornecedor;

            } catch (Exception ex) {
                printEntradaInvalida();
            }
        }
    }

    // Função para filtrar o relatório
    public static StatusConta escolherFiltroStatus() {

        System.out.println("Filtro de Status:");
        System.out.println("1 - Pago");
        System.out.println("2 - Pendente");
        System.out.println("3 - Cancelado");

        try {
            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1:
                    return StatusConta.PAGO;

                case 2:
                    return StatusConta.PENDENTE;

                case 3:
                    return StatusConta.CANCELADO;

                default:
                    return null; // TODOS
            }

        } catch (Exception ex) {
            System.out.println("Entrada inválida! Usando filtro padrão (TODOS).");
            return null;
        }
    }


    // Função para gerar relatório de contas a receber
    public static void relatorioContasReceber(RelatorioLocal relatorio) {

        try {
            System.out.println("\n===== RELATÓRIO CONTAS A RECEBER =====");

            StatusConta filtro = escolherFiltroStatus();

            List<ContaReceber> lista = relatorio.contasReceber(null, null, filtro);

            if (lista.isEmpty()) {
                System.out.println("Nenhuma conta registrada!");
                return;
            }

            for (ContaReceber c : lista) {
                System.out.println(
                        "ID: " + c.getId()
                                + " | Cliente: " + (c.getCliente() == null
                                ? "Venda sem cliente"
                                : c.getCliente().getNome())
                                + " | Valor: R$ " + c.getValor()
                                + " | Emissão: " + c.getDataEmissao()
                                + " | Vencimento: " + c.getDataVencimento()
                                + " | Pagamento: " + (c.getDataPagamento() == null
                                ? "Não pago"
                                : c.getDataPagamento())
                );
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Função para gerar relatório de contas a pagar
    public static void relatorioContasPagar(RelatorioLocal relatorio) {

        try {
            System.out.println("\n===== RELATÓRIO CONTAS A PAGAR =====");

            StatusConta filtro = escolherFiltroStatus();

            List<ContaPagar> lista = relatorio.contasPagar(null, null, filtro);

            if (lista.isEmpty()) {
                System.out.println("Nenhuma conta registrada!");
                return;
            }

            for (ContaPagar c : lista) {
                System.out.println(
                        "ID: " + c.getId()
                                + " | Fornecedor: " + (c.getFornecedor() == null
                                ? "Sem fornecedor"
                                : c.getFornecedor().getNome())
                                + " | Valor: R$ " + c.getValor()
                                + " | Emissão: " + c.getDataEmissao()
                                + " | Vencimento: " + c.getDataVencimento()
                                + " | Pagamento: " + (c.getDataPagamento() == null
                                ? "Não pago"
                                : c.getDataPagamento())
                                + " | Status: " + c.getStatus()
                );
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void relatorioContasVencidas(RelatorioLocal relatorio) {

        try {
            System.out.println("\n===== RELATÓRIO CONTAS A PAGAR VENCIDAS =====");

            System.out.print("Data inicial (AAAA-MM-DD): ");
            LocalDate inicio = LocalDate.parse(sc.nextLine());

            System.out.print("Data final (AAAA-MM-DD): ");
            LocalDate fim = LocalDate.parse(sc.nextLine());

            List<ContaPagar> contas = relatorio.contasPagar(inicio, fim, null);

            List<ContaPagar> vencidas = new ArrayList<>();

            LocalDate hoje = LocalDate.now();

            for (ContaPagar c : contas) {
                if (c.getDataVencimento().isBefore(hoje) && c.getStatus() != StatusConta.PAGO) {
                    vencidas.add(c);
                }
            }

            if (vencidas.isEmpty()) {
                System.out.println("Nenhuma conta vencida!");
                return;
            }

            for (ContaPagar c : vencidas) {
                System.out.println(
                        "ID: " + c.getId()
                                + " | Fornecedor: " + c.getFornecedor().getNome()
                                + " | Valor: R$ " + c.getValor()
                                + " | Emissão: " + c.getDataEmissao()
                                + " | Vencimento: " + c.getDataVencimento()
                                + " | Status: " + c.getStatus()
                );
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    // Função para gerar relatório de estoque baixo
    public static void relatorioEstoqueBaixo(RelatorioLocal relatorio) {

        System.out.println("\n===== PRODUTOS COM ESTOQUE BAIXO =====");

        List<Produto> lista = relatorio.produtosEstoqueBaixo();

        if (lista.isEmpty()) {
            System.out.println("Nenhum produto com estoque abaixo do mínimo");
            return;
        }

        for (Produto p : lista) {
            System.out.println(
                    "Produto: " + p.getNome()
                            + " | Estoque: " + p.getQuantidadeEstoque()
                            + " | Mínimo: " + p.getEstoqueMinimo()
            );
        }
    }

    public static void relatoriotodosProdutos(ProdutoService produtoService) {

        List<Produto> produtos = produtoService.listarTodos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
            return;
        }

        System.out.println("\n===== ESTOQUE PRODUTOS =====");

        for (Produto produto : produtos) {

            boolean baixoEstoque = produto.getQuantidadeEstoque() <= produto.getEstoqueMinimo();

            String cor = baixoEstoque ? "\u001B[31m" : "";

            System.out.println(
                    cor +
                            "ID: " + produto.getId()
                            + " | Nome: " + produto.getNome()
                            + " | Preço Custo: R$ " + produto.getPrecoCompra()
                            + " | Preço Venda: R$ " + produto.getPrecoVenda()
                            + " | Quantidade Estoque: " + produto.getQuantidadeEstoque()
                            + " | Estoque Mínimo: " + produto.getEstoqueMinimo()
                            + "\u001B[0m"
            );
        }
    }

    public static void abrirCaixa(CaixaService caixaService) {

        try {
            Caixa caixa = new Caixa();

            System.out.print("Valor de abertura: ");
            double valor = Double.parseDouble(sc.nextLine());



            caixa.setValorAbertura(valor);

            caixaService.abrirCaixa(caixa);

            System.out.println("Caixa aberto com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao abrir caixa: " + e.getMessage());
        }
    }

    public static void fecharCaixa(CaixaService caixaService, Long id) {

        try {
            Caixa caixa = caixaService.buscar(id);

            if (caixa == null) {
                System.out.println("Caixa não encontrado!");
                return;
            }

            caixaService.fecharCaixa(caixa);

            System.out.println("Caixa fechado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao fechar caixa: " + e.getMessage());
        }
    }

    public static void movimentarCaixa(MovimentacaoCaixaService service, Caixa caixa) {

        try {
            MovimentacaoCaixa mov = new MovimentacaoCaixa();

            System.out.println("1 - Entrada | 2 - Saída");
            int opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 1) {
                mov.setTipo(TipoMovimento.ENTRADA);
            } else {
                mov.setTipo(TipoMovimento.SAIDA);
            }

            System.out.print("Valor: ");
            mov.setValor(Double.parseDouble(sc.nextLine()));

            System.out.print("Descrição: ");
            mov.setDescricao(sc.nextLine());

            mov.setCaixa(caixa);

            service.registrarMovimentacao(mov);

            System.out.println("Movimentação registrada!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void verSaldo(MovimentacaoCaixaService service, Caixa caixa) {

        double saldo = service.calcularSaldo(caixa);

        System.out.println("Saldo atual do caixa: R$ " + saldo);
    }

    public static void printEntradaInvalida(){
        System.out.println("===================");
        System.out.println("|Entrada inválida!|");
        System.out.println("===================");
        sc.nextLine();
    }


}
