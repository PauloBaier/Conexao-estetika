import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Vaga> vagas = new ArrayList<>();


        for (int i = 1; i <= 5; i++) {
            vagas.add(new Vaga(i));
        }

        int opcao = 0;


        while (opcao != 4) {

            System.out.println("\n====== MENU ====");
            System.out.println("|1 - Listar vagas|");
            System.out.println("|2 - Ocupar vaga | ");
            System.out.println("|3 - Liberar vaga|               ^");
            System.out.println("|4 - Sair        |               ^");
            System.out.println("|Role para cima para ver as vags |");
            System.out.print("|Escolha: ");

            opcao = sc.nextInt();


            if (opcao == 1) {
                for (Vaga v : vagas) {
                    System.out.println(v);
                }
            } else if (opcao == 2) {

                System.out.print("Digite o número da vaga: ");
                int numero = sc.nextInt();
                sc.nextLine();

                if (numero >= 1 && numero <= 5) {

                    Vaga vaga = vagas.get(numero - 1);

                    if (vaga.isOcupada()) {
                        System.out.println("Essa vaga já está ocupada!");
                    } else {
                        System.out.print("Digite a placa: ");
                        String placa = sc.nextLine();

                        vaga.setPlaca(placa);
                        vaga.setOcupada(true);

                        System.out.println("Vaga ocupada com sucesso!");
                    }

                } else {
                    System.out.println("Número de vaga inválido!");
                }
            }

            // 5️⃣ Liberar vaga
            else if (opcao == 3) {

                System.out.print("Digite o número da vaga: ");
                int numero = sc.nextInt();

                if (numero >= 1 && numero <= 5) {

                    Vaga vaga = vagas.get(numero - 1);

                    if (!vaga.isOcupada()) {
                        System.out.println("Essa vaga já está livre!");
                    } else {
                        vaga.setPlaca("");
                        vaga.setOcupada(false);

                        System.out.println("Vaga liberada com sucesso!");
                    }

                } else {
                    System.out.println("Número inválido!");
                }
            } else if (opcao != 4) {
                System.out.println("Opção inválida!");
            }
        }

        sc.close();
        System.out.println("Sistema encerrado.");
    }
}