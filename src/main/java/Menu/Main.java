package Menu;
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
                  cadastroCliente();
                    break;
                case 2:
                    cadastroFornecedor();
                    break;
                case 3:
                    cadastroProduto();
                    break;
                case 4:
                    listarCliente();
                    break;
                case 5:
                    listarFornecedor();
                    break;
                case 6:
                    listarProduto();
                    break;
                case 7:
                    removerCliente();
                    break;
                case 8:
                    removerFornecedor();
                    break;
                case 9:
                    removerProduto();
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
                    System.out.println("Adicionar Cliente...");
                    System.out.println("Adicionar Produtos...");
                    System.out.println("Fechar Venda...");
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

        System.out.print("ID: " + id);

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

        System.out.print("ID: " + id);

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

        System.out.print("ID: " + id);

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

    public void listarCliente() {

        for(Cliente c : listaClientes()) {
            System.out.println("ID: " + c.getId()
                    + "| Nome: " + c.getNome()
                    + "| Telefone" + c.getTelefone()
                    + "| Email: " + c.getEmail()
                    + "| CPF: " + c.getCpf()
                    + "| Dinheiro Gasto: " + c.getDinheiroGasto());
        }
    }

    public void listarFornecedor() {

        for(Fornecedor f : listaFornecedores()) {
            System.out.println("ID: " + f.getId()
                    + "| Nome: " + f.getNome()
                    + "| Telefone" + f.getTelefone()
                    + "| Email: " + f.getEmail()
                    + "| CNPJ: " + f.getCnpj()
                    + "| Razão Social: " + f.getRazaoSocial());
        }
    }

    public void listarProduto() {
        for(Produto p : listaProdutos()) {
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

        listarCliente();

        System.out.print("Digite o ID do cliente que deseja remover: ");
        int id = sc.nextInt();

        boolean removido = sistema.removerCliente(id);

        if(removido){
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void removerFornecedor(Sistema sistema) {

        listarFornecedor();

        System.out.print("Digite o ID do fornecedor que deseja remover: ");
        int id = sc.nextInt();

        boolean removido = sistema.removerFornecedor(id);

        if(removido){
            System.out.println("Fornecedor removido com sucesso!");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    public void removerProduto(Sistema sistema) {

        listarProduto();

        System.out.print("Digite o ID do produto que deseja remover: ");
        int id = sc.nextInt();

        boolean removido = sistema.removerProduto(id);

        if(removido){
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
