package ProjetoMensal_1;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        try {

            System.out.println("TESTE CONTA A PAGAR");

            ContaPagar conta1 = new ContaPagar(
                    1,
                    500.0,
                    LocalDate.now(),
                    "Produtos de limpeza",
                    "Fornecedor ABC",
                    LocalDate.now().plusDays(10)
            );

            System.out.println("Conta criada com sucesso!");
            System.out.println("Fornecedor: " + conta1.getFornecedor());
            System.out.println("Pago? " + conta1.estaPago());

            conta1.setPago(true);
            System.out.println("Pago após alteração? " + conta1.estaPago());

            // Teste erro
            conta1.setPago(true);

        } catch (Exception e) {
            System.out.println("ERRO CAPTURADO: " + e.getMessage());
        }

        try {

            System.out.println("\n TESTE CONTA A RECEBER");

            ContaReceber conta2 = new ContaReceber(
                    2,
                    1000.0,
                    LocalDate.now(),
                    "Venda 123",
                    "João"
            );

            System.out.println("Conta criada com sucesso!");
            System.out.println("Cliente: " + conta2.getCliente());

        } catch (Exception e) {
            System.out.println("ERRO CAPTURADO: " + e.getMessage());
        }
    }
}
