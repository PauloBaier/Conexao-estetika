package services;

import models.ContaPagar;
import models.Fornecedor;
import models.ItemVenda;
import models.Produto;
import models.enums.StatusConta;

import java.time.LocalDate;
import java.util.List;

public class EntradaEstoqueService {

    private final ProdutoService produtoService;
    private final ContaPagarService contaPagarService;

    public EntradaEstoqueService(ProdutoService produtoService, ContaPagarService contaPagarService) {
        this.produtoService = produtoService;
        this.contaPagarService = contaPagarService;
    }

    public void registrarEntradaEstoque(
            List<ItemVenda> itens,
            Fornecedor fornecedor,
            double valor,
            LocalDate vencimento
    ) {

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A entrada deve ter pelo menos um item.");
        }

        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor obrigatório.");
        }

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor total inválido.");
        }

        if (vencimento == null) {
            throw new IllegalArgumentException("Data de vencimento obrigatória.");
        }

        for (ItemVenda item : itens) {
            if (item == null || item.getProduto() == null) {
                throw new IllegalArgumentException("Item ou produto inválido.");
            }

            if (item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida para o produto.");
            }

            Produto produto = produtoService.buscarPorId(item.getProduto().getId());

            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado: " + item.getProduto().getId());
            }

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() + item.getQuantidade()
            );

            produtoService.atualizar(produto);
        }

        ContaPagar conta = new ContaPagar();
        conta.setDescricao("Compra de produtos do fornecedor " + fornecedor.getNome());
        conta.setFornecedor(fornecedor);
        conta.setValor(valor);
        conta.setDataEmissao(LocalDate.now());
        conta.setDataVencimento(vencimento);
        conta.setDataPagamento(null);
        conta.setTipoDespesas("ENTRADA_ESTOQUE");
        conta.setStatus(StatusConta.PENDENTE);

        contaPagarService.cadastrar(conta);
    }
}