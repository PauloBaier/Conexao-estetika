package service;

import model.Fornecedor;
import model.ItemVenda;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

public class EntradaEstoqueService {

    public static void registrarEntradaEstoque(
            List<ItemVenda> itens,
            Fornecedor fornecedor,
            double valor,
            LocalDate vencimento
    ) {

        for (ItemVenda item : itens) {

            var produto = item.getProduto();

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() + item.getQuantidade()
            );

            System.out.println("Entrada: " + produto.getNome()
                    + " + " + item.getQuantidade());
        }

        System.out.println("Fornecedor: " + fornecedor.getNome());
        System.out.println("Valor total entrada: R$ " + valor);
        System.out.println("Vencimento: " + vencimento);
    }
}
