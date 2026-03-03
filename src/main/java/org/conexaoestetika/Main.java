package org.conexaoestetika;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Cadastros<Produto> produtos = new Cadastros<>();
        Cadastros<Cliente> clientes = new Cadastros<>();
        Cadastros<Fornecedor> fornecedores = new Cadastros<>();

        clientes.adicionar(
                new Cliente(
                        1, "Adão", "123456789", "Adao@gmail.com", "12345678901"
                )
        );

        fornecedores.adicionar(
                new Fornecedor(
                        1,
                        "Serpente",
                        "40028922",
                        "ssssssssss@gmail.com",
                        "03031053000168",
                        "Destruir O Mundo"
                )
        );

        produtos.adicionar(
                new Produto(
                        1,
                        "Maca",
                        "Maca",
                        25.0,
                        -2.0,
                        -1,
                        "Eden",
                        fornecedores.buscarId(1)
                )
        );

        Cliente cliente = clientes.buscarId(1);
        System.out.println("Nome: " + cliente.getNome() + " telefone: " + cliente.getTelefone() );


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

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Cliente...");
                    break;
                case 2:
                    System.out.println("Cadastrar Fornecedor...");
                    break;
                case 3:
                    System.out.println("Cadastrar Produto...");
                    break;
                case 4:
                    System.out.println("Listar Clientes...");
                    break;
                case 5:
                    System.out.println("Listar Fornecedores...");
                    break;
                case 6:
                    System.out.println("Listar Produtos...");
                    break;
                case 7:
                    System.out.println("Remover Cliente...");
                    break;
                case 8:
                    System.out.println("Remover Fornecedor...");
                    break;
                case 9:
                    System.out.println("Remover Produto...");
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
}
