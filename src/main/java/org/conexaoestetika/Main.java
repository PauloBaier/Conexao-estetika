package org.conexaoestetika;
import org.conexaoestetika.models.*;
import org.conexaoestetika.models.enums.FiltroStatus;
import org.conexaoestetika.models.enums.FormaPagamento;
import org.conexaoestetika.models.enums.StatusVenda;
import org.conexaoestetika.services.Sistema;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class Main {

    static Sistema sistema = new Sistema();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = -1;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Venda");
            System.out.println("3 - Financeiro");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try{
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
            }catch(Exception ex){
                printEntradaInvalida();
            }

        } while (opcao != 0);
    }

    public static void menuCadastros() {
        int opcao = -1;

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

            try {

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
            }
            catch(Exception ex){
                printEntradaInvalida();
            }

        } while (opcao != 0);
    }

    public static void menuVenda() {
        int opcao = -1;

        do {
            System.out.println("\n===== MENU VENDA =====");
            System.out.println("1 - Nova Venda");
            System.out.println("0 - Voltar");

            try {
                opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        novaVenda(sistema);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            catch(Exception ex){
                printEntradaInvalida();
            }

        } while (opcao != 0);
    }

    public static void menuFinanceiro() {
        int opcao = -1;

        do {
            System.out.println("\n===== MENU FINANCEIRO =====");
            System.out.println("1 - Contas a Receber");
            System.out.println("2 - Contas a Pagar");
            System.out.println("3 - Entrada de Produtos");
            System.out.println("0 - Voltar");

            try {
                opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        contasReceber(sistema);
                        break;
                    case 2:
                        contasPagar(sistema);
                        break;
                    case 3:
                        entradaDeProdutos(sistema);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            catch (Exception ex){
                printEntradaInvalida();
            }

        } while (opcao != 0);
    }

    public static void menuRelatorios() {
        int opcao = -1;

        do {
            System.out.println("\n===== MENU RELATÓRIOS =====");
            System.out.println("1 - Vendas");
            System.out.println("2 - Contas a Pagar");
            System.out.println("3 - Contas a pagar vencidas");
            System.out.println("4 - Produtos (Estoque abaixo do mínimo)");
            System.out.println("5 - Produtos");
            System.out.println("0 - Voltar");

            try {
                opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        relatorioContasReceber(sistema);
                        break;
                    case 2:
                        relatorioContasPagar(sistema);
                        break;
                    case 3:
                        relatorioContasVencidas(sistema);
                        break;
                    case 4:
                        relatorioEstoqueBaixo(sistema);
                        break;
                    case 5:
                        relatoriotodosProdutos(sistema);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            catch (Exception ex){
                printEntradaInvalida();
            }

        } while (opcao != 0);
    }

    // Cadastro Cliente
    public static void cadastroCliente(Sistema sistema) {

        System.out.println("ID: " + (sistema.cadastroClientes.getUltimoId() + 1));
        sc.nextLine();

        try{
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("CPF (11 números): ");
            String cpf = sc.nextLine();


            if(sistema.novoCadastroCliente(nome, telefone, email, cpf)){
                System.out.println("Cliente cadastro com sucesso!");
            }
        }
        catch(Exception ex){
            System.out.println("===================");
            System.out.println("|Entrada inválida!|");
            System.out.println("===================");
            sc.nextLine();
        }
    }

    // Cadastro Fornecedor
    public static void cadastroFornecedor(Sistema sistema) {

        System.out.println("ID: " + (sistema.cadastroFornecedores.getUltimoId() + 1));
        sc.nextLine();

        try{
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

            if (sistema.novoCadastroFornecedor(nome, telefone, email, cnpj, razaoSocial)) {
                System.out.println("Fornecedor cadastrado com sucesso!");
            }
        }
        catch (Exception ex){
            System.out.println("===================");
            System.out.println("|Entrada inválida!|");
            System.out.println("===================");
            sc.nextLine();
        }
    }

    // Cadastro Produto
    public static void cadastroProduto(Sistema sistema) {

        System.out.println("ID: " + (sistema.cadastroProdutos.getUltimoId() + 1));
        sc.nextLine();

        try{
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

            if (sistema.novoCadastroProduto(nome, descricao, precoCusto, precoVenda, qntdEstoque, estoqueMinimo, fornecedor)) {
                System.out.println("Produto cadastrado com sucesso!");
            }
        }
        catch(Exception ex){
            System.out.println("===================");
            System.out.println("|Entrada inválida!|");
            System.out.println("===================");
            sc.nextLine();
        }
    }

    // Loop que exibe todos os fornecedores e escolhe por id
    public static Fornecedor escolherFornecedor(Sistema sistema) {
        List<Fornecedor> fornecedores = sistema.cadastroFornecedores.listarTodos();

        if(fornecedores.isEmpty()){
            System.out.println("Nenhum Fornecedor Cadastrado!");
            return null;
        }

        for (Fornecedor f : fornecedores ) {
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
        List<Cliente> clientes = sistema.cadastroClientes.listarTodos();

        if(clientes.isEmpty()){
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        for(Cliente c : clientes) {
            System.out.println("ID: " + c.getId()
                    + "| Nome: " + c.getNome()
                    + "| Telefone: " + c.getTelefone()
                    + "| Email: " + c.getEmail()
                    + "| CPF: " + c.getCpf()
                    + "| Dinheiro Gasto: " + c.getDinheiroGasto());
        }
    }

    // Função para listar todos os fornecedores cadastrados
    public static void listarFornecedor(Sistema sistema) {
        List<Fornecedor> fornecedores = sistema.cadastroFornecedores.listarTodos();

        if(fornecedores.isEmpty()){
            System.out.println("Nenhum Fornecedor Cadastrado!");
            return;
        }

        for(Fornecedor f : fornecedores) {
            System.out.println("ID: " + f.getId()
                    + "| Nome: " + f.getNome()
                    + "| Telefone: " + f.getTelefone()
                    + "| Email: " + f.getEmail()
                    + "| CNPJ: " + f.getCnpj()
                    + "| Razão Social: " + f.getRazaoSocial());
        }
    }

    // Função para listar todos os produtos cadastrados
    public static void listarProduto(Sistema sistema) {

        List<Produto> produtos = sistema.cadastroProdutos.listarTodos();

        if(produtos.isEmpty()){
            System.out.println("Nenhum Produto Cadastrado!");
            return;
        }

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
        List<Cliente> clientes = sistema.cadastroClientes.listarTodos();
        int idEscolhido;

        if(clientes.isEmpty()){
            System.out.println("Nenhum Cliente Cadastrado: Compra sem cliente");
            return null;
        }

        for(Cliente c : sistema.cadastroClientes.listarTodos()) {
            System.out.println("ID: " + c.getId() + "Nome: " + c.getNome());
        }

        while(true){
            try {
                System.out.println("Digite o ID do Cliente ou 0 para continuar sem cliente: ");
                idEscolhido = sc.nextInt();
                break;
            }
            catch (Exception ex){
                printEntradaInvalida();
            }
        }


        if(idEscolhido == 0) {
            return null;
        }

        for (Cliente c : sistema.cadastroClientes.listarTodos()) {
            if (c.getId() == idEscolhido) {
                return c;
            }
        }

        System.out.println("Cliente não encontrado: Compra sem Cliente");
        return null;
    }

    // Função para adicionar produto a venda e exibir como um carrinho
    public static void adicionarProdutoVenda(Sistema sistema) {

        while (true) {

            List<Produto> produtos = sistema.cadastroProdutos.listarTodos();
            int produtoEscolhido = -3;

            if(produtos.isEmpty()){
                System.out.println("Nenhum Produto Cadastrado: Cadastre Produtos antes de iniciar Nova Venda!");
                System.out.println("Precione Enter para continuar");
                sc.nextLine();
                sc.nextLine();
                return;
            }

            // LISTAR PRODUTOS
            for (Produto p : produtos) {
                System.out.println("ID: " + p.getId()
                        + " | Nome: " + p.getNome()
                        + " | Preço: " + p.getPrecoVenda()
                        + " | Estoque: " + p.getQuantidadeEstoque());
            }

            System.out.println("Digite o Id do produto ou (-1 Cancelar / -2 Pagamento): ");


            try{
                produtoEscolhido = sc.nextInt();

                if (produtoEscolhido == -1) {
                    sistema.cancelarVendaAtual();
                    System.out.println("Venda cancelada!");
                    break;
                }

                if (produtoEscolhido == -2) {

                    List<ItemVenda> produtosVenda = sistema.getListaVendaAtual();

                    if (produtosVenda.isEmpty()) {
                        System.out.println("Venda Vazia!");
                        sistema.cancelarVendaAtual();
                        sc.nextLine();
                        sc.nextLine();
                        return;
                    }

                    double total = 0;

                    System.out.println("\n===== RESUMO DA VENDA =====");

                    for (ItemVenda item : produtosVenda) {

                        System.out.println(
                                "Produto: " + item.getProduto().getNome()
                                        + " | Quantidade: " + item.getQuantidade()
                                        + " | Valor Unitário: " + item.getProduto().getPrecoVenda()
                                        + " | Subtotal: " + item.getSubtotal()
                        );
                    }

                    System.out.println("TOTAL DA VENDA: R$ " + sistema.getTotalVendaAtual());
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

                if(produtoSelecionado.getQuantidadeEstoque() < quantidade){
                    System.out.println("Estoque Insuficiente!");
                    continue;
                }

                sistema.adicionarProdutoVenda(produtoSelecionado, quantidade);

                System.out.println("Produto adicionado ao carrinho!");
            }
            catch (Exception ex) {
                printEntradaInvalida();
            }
        }

    }

    // Função para finalizar a venda e registar qual metodo de pagamento foi utilizado e registar status da venda
    public static void finalizarVenda(Sistema sistema) {
        boolean pago =  false;

        while(true) {
            int numeroEscolhido = -1;


            System.out.println("1 - Dinheiro | 2 - Pix | 3 - Cartão | 4 - Cancelar venda");

            try {
                numeroEscolhido = sc.nextInt();
            }
            catch (Exception ex) {
                printEntradaInvalida();
                continue;
            }

            switch (numeroEscolhido) {
                case 1:
                    pago = pagamentoDinheiro();
                    if(!pago) {
                        continue;
                    }else{
                        sistema.alterarStatusVendaAtual(StatusVenda.PAGO);
                    }
                    break;
                case 2:
                    sistema.getVendaAtual().alterarFormaPagamento(FormaPagamento.PIX);
                    break;
                case 3:
                    sistema.getVendaAtual().alterarFormaPagamento(FormaPagamento.CARTAO);
                    break;
                case 4:
                    sistema.cancelarVendaAtual();
                    return;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }

            if(!pago){
                while(true){
                    try{
                        System.out.println("Pagamento teve sucesso? 1-sim 2-não 3-cancelar");
                        int opcao = sc.nextInt();

                        switch (opcao) {
                            case 1:
                                sistema.alterarStatusVendaAtual(StatusVenda.PAGO);
                                break;
                            case 2:
                                sistema.alterarStatusVendaAtual(StatusVenda.PENDENTE);
                                break;
                            case 3:
                                sistema.cancelarVendaAtual();
                                return;
                            default:
                                System.out.println("Opção inválida!");
                                continue;
                        }

                        break;
                    }
                    catch (Exception ex){
                        System.out.println("===================");
                        System.out.println("|Entrada inválida!|");
                        System.out.println("===================");
                        sc.nextLine();
                    }
                }
            }

            if(sistema.registrarVendaAtual()) {
                System.out.println("Venda finalizada com sucesso!");
            }
            else{
                System.out.println("Erro ao registrar venda!");
            }

            break;
        }
    }

    public static boolean pagamentoDinheiro(){
        int descontoOpcao = -3;

        // DESCONTO
        do {
            System.out.println("Total da venda: R$ " + sistema.getTotalVendaAtual());
            System.out.println("Deseja aplicar desconto? (1-Sim / 2-Não)");
            try {
                descontoOpcao = sc.nextInt();


                if (descontoOpcao == 1) {
                    System.out.println("Digite o valor do desconto %: ");
                    double desconto = sc.nextDouble();

                    sistema.getVendaAtual().setDesconto(desconto);
                    break;
                }
                else if( descontoOpcao == 2){
                    break;
                }
                else{
                    System.out.println("Opção Inválida");
                }

            } catch (Exception ex) {
                printEntradaInvalida();
                continue;
            }
        }while(true);
        System.out.println("Total final: R$ " + sistema.getTotalVendaAtual());

        // PAGAMENTO EM DINHEIRO (CALCULAR TROCO)
        double valorPago, troco;

        do {
            System.out.println("Valor recebido: ");
            valorPago = sc.nextDouble();

            if(valorPago == -1) return false;

            if (valorPago < sistema.getTotalVendaAtual()) {
                System.out.println("Valor insuficiente!");
                System.out.println("Digite -1 para cancelar Venda");
            }

            troco = valorPago - sistema.getTotalVendaAtual();
        } while (valorPago < sistema.getTotalVendaAtual());


        System.out.printf("Troco: R$ %.2f\n", troco);
        return true;
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

        while(true){
            int id = 0;
            try {
                for (ContaReceber c : sistema.listaTodasContasReceber(false)) {

                    System.out.println(
                            "ID: " + c.getId()
                                    + " | Cliente: " + (c.getCliente() == null? "Venda sem cliente": c.getCliente().getNome())
                                    + " | Valor: R$ " + c.getValor()
                                    + " | Data: " + c.getData()
                    );
                }

                System.out.print("Digite o ID da conta para marcar como paga (0 para voltar): ");
                id = sc.nextInt();


                if (id != 0) {

                    if (sistema.receberConta(id)) {
                        System.out.println("Conta marcada como paga!");
                    } else {
                        System.out.println("Erro ao marcar conta.");
                    }
                }

                break;
            }
            catch (InputMismatchException ex){
                printEntradaInvalida();
                continue;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

        }


    }

    // Funçãp para exibir todas as contas que não foram pagas e precisam ser fechadas e pagas
    public static void contasPagar(Sistema sistema) {

        System.out.println("\n===== CONTAS A PAGAR =====");

        while (true){
            int id = 0;
            try {
                for (ContaPagar c : sistema.listaTodasContasPagar(false)) {

                    System.out.println(
                            "ID: " + c.getId()
                                    + " | Fornecedor: " + c.getFornecedor().getNome()
                                    + " | Valor: R$ " + c.getValor()
                                    + " | Vencimento: " + c.getVencimento()
                    );
                }

                System.out.print("Digite o ID da conta para pagar (0 para voltar): ");
                id = sc.nextInt();

                if (id != 0) {

                    if (sistema.PagarConta(id)) {
                        System.out.println("Conta paga com sucesso!");
                    } else {
                        System.out.println("Erro ao pagar conta.");
                    }
                }

                break;
            }
            catch (InputMismatchException ex){
                printEntradaInvalida();
                continue;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

        }


    }

    public static void entradaDeProdutos(Sistema sistema){
        //public boolean novaEntrada(double valor, List<ItemVenda> produtos, Fornecedor fornecedor, LocalDate vencimento)
        double valor = 0;
        List<ItemVenda> produtos;
        Fornecedor fornecedor;
        LocalDate vencimento;

        produtos = adicionarProdutosEntrada(sistema);

        if(produtos.isEmpty()) return;

        fornecedor = adicionarFornecedorEntrada(sistema);

        if (fornecedor == null) return;

        while(true){
            try{
                System.out.println("Digite a Data de Vencimento: (AAAA-MM-DD) (-1 Cancelar)");
                String entrada = sc.next();

                if(entrada.equalsIgnoreCase("-1")) return;

                vencimento = LocalDate.parse(entrada);
                break;
            }
            catch (Exception ex){
                printEntradaInvalida();
                continue;
            }
        }

        for (ItemVenda item: produtos){
            valor += item.getSubtotal();
        }

        sistema.novaEntrada(valor, produtos, fornecedor, vencimento);

    }

    public static List<ItemVenda> adicionarProdutosEntrada(Sistema sistema){
        List<ItemVenda> produtos = new ArrayList<>();

        while (true){
            Produto produto = null;
            int quantidade = -1;

            int opcao = -3;
            listarProduto(sistema);
            System.out.println("Digite o codigo do Produto: (-1 para continuar/ -2 para cancelar)");
            try{
                opcao = sc.nextInt();

                if(opcao == -1){
                    return produtos;
                }

                if(opcao == -2){
                    return new ArrayList<>();
                }

                try{
                    produto = sistema.cadastroProdutos.buscarId(opcao);
                }
                catch (Exception ex){
                    System.out.println("Produto não encontrado!");
                    continue;
                }

                System.out.println("Digite a quantidade:");

                quantidade = sc.nextInt();

                if(quantidade <= 0){
                    System.out.println("Quantidade deve ser um valor positivo acima de zero!");
                    continue;
                }

            }
            catch (Exception ex){
                printEntradaInvalida();
                continue;
            }

            produtos.add(new ItemVenda(produto, quantidade));

        }

    }

    public static Fornecedor adicionarFornecedorEntrada(Sistema sistema){
        Fornecedor fornecedor;

        while(true){
            int opcao = -3;
            listarFornecedor(sistema);
            try{
                System.out.println("Digite o codigo do Fornecedor: (-1 para cancelar)");
                opcao = sc.nextInt();

                if(opcao == -1) return null;

                fornecedor = sistema.cadastroFornecedores.buscarId(opcao);

                return fornecedor;
            }
            catch (InputMismatchException ex){
                printEntradaInvalida();
            }
            catch (Exception ex){
                System.out.println("Fornecedor não encontrado!");
            }
        }

    }

    // Função para filtrar o relatório
    public static FiltroStatus escolherFiltroStatus() {

        System.out.println("Filtro de Status:");
        System.out.println("1 - Todos");
        System.out.println("2 - Pagos");
        System.out.println("3 - Pendentes");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 2:
                return FiltroStatus.PAGO;

            case 3:
                return FiltroStatus.ABERTO;

            default:
                return FiltroStatus.TODOS;
        }
    }

    // Função para gerar relatório de contas a receber
    public static void relatorioContasReceber(Sistema sistema) {

        try {
            System.out.println("\n===== RELATÓRIO CONTAS A RECEBER =====");

            System.out.print("Data inicial (AAAA-MM-DD): ");
            LocalDate dataInicial = LocalDate.parse(sc.next());

            System.out.print("Data final (AAAA-MM-DD): ");
            LocalDate dataFinal = LocalDate.parse(sc.next());

            FiltroStatus filtro = escolherFiltroStatus();

            List<ContaReceber> lista = sistema.listarContasReceber(dataInicial, dataFinal, filtro);

            if(lista.isEmpty()){
                System.out.println("Nunhuma conta registrada!");
                return;
            }

            for (ContaReceber c : lista) {

                System.out.println(
                                "ID: " + c.getId()
                                + " | Cliente: " + (c.getCliente() == null? "Venda sem cliente": c.getCliente().getNome())
                                + " | Valor: R$ " + c.getValor()
                                + " | Data: " + c.getData()
                                + " | Pago: " + c.estaPago()
                );
            }
        }
        catch (DateTimeParseException ex){
            printEntradaInvalida();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Função para gerar relatório de contas a pagar
    public static void relatorioContasPagar(Sistema sistema) {

        try {
            System.out.println("\n===== RELATÓRIO CONTAS A PAGAR =====");

            System.out.print("Data inicial (AAAA-MM-DD): ");
            LocalDate dataInicial = LocalDate.parse(sc.next());

            System.out.print("Data final (AAAA-MM-DD): ");
            LocalDate dataFinal = LocalDate.parse(sc.next());

            FiltroStatus filtro = escolherFiltroStatus();

            List<ContaPagar> lista = sistema.listarContasPagar(dataInicial, dataFinal, filtro);

            if(lista.isEmpty()){
                System.out.println("Nenhuma conta registrada!");
                return;
            }

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
        catch (DateTimeParseException ex){
            printEntradaInvalida();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void relatorioContasVencidas(Sistema sistema){
        List<ContaPagar> contasVencidas = sistema.listaContaPagarVencidas();

        System.out.println("===== RELATÓRIO CONTAS A PAGAR VENCIDAS =====");

        if(contasVencidas.isEmpty()){
            System.out.println("Nunhuma conta vencida!");
            return;
        }

        for (ContaPagar c : contasVencidas) {

            System.out.println(
                    "ID: " + c.getId()
                            + " | Fornecedor: " + c.getFornecedor().getNome()
                            + " | Valor: R$ " + c.getValor()
                            + " | Vencimento: " + c.getVencimento()
                            + " | Data: " + c .getData()
            );
        }
    }


    // Função para gerar relatório de estoque baixo
    public static void relatorioEstoqueBaixo(Sistema sistema) {

        System.out.println("\n===== PRODUTOS COM ESTOQUE BAIXO =====");

        List<Produto> lista = sistema.listarProdutosEstoqueAbaixoMin();

        if(lista.isEmpty()){
            System.out.println("Nenhum Produto com estoque abaixo do Minimo");
            return;
        }

        for(Produto p : lista) {

            System.out.println(
                    "Produto: " + p.getNome()
                            + " | Estoque: " + p.getQuantidadeEstoque()
                            + " | Mínimo: " + p.getEstoqueMinimo()
            );
        }
    }

    public static void relatoriotodosProdutos(Sistema sistema){
        List<Produto> produtos = sistema.cadastroProdutos.listarTodos();

        if(produtos.isEmpty()){
            System.out.println("Nenhum Produto Cadastrado!");
            return;
        }

        System.out.println("\n===== ESTOQUE PRODUTOS =====");

        for (Produto produto: produtos){
            String formatacaoEstoqueBaixo = produto.precisaReporEstoque() ? "\u001B[31m" : "";

            System.out.println(
                    formatacaoEstoqueBaixo
                    +"ID: " + produto.getId()
                    +" | Nome: " + produto.getNome()
                    +" | Preço Custo: R$" + produto.getPrecoCusto()
                    +" | Preço Venda: R$" + produto.getPrecoVenda()
                    +" | Quantidade Estoque: " + produto.getQuantidadeEstoque()
                    +" | Estoque Mínimo: " + produto.getEstoqueMinimo()
                    +"\u001B[0m"
            );
        }
    }

    public static void printEntradaInvalida(){
        System.out.println("===================");
        System.out.println("|Entrada inválida!|");
        System.out.println("===================");
        sc.nextLine();
    }

}
