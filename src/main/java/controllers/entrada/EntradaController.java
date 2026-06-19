package controllers.entrada;

import controllers.entrada.dto.FornecedorEntradaResponse;
import controllers.entrada.dto.ProdutoEntradaResponse;
import models.Fornecedor;
import models.ItemVenda;
import models.Produto;

import java.time.LocalDate;
import java.util.List;

public interface EntradaController {

    List<FornecedorEntradaResponse> listarFornecedores();
    Fornecedor buscarFornecedorPorIndice(int indice);

    List<ProdutoEntradaResponse> listarProdutos();
    Produto buscarProdutoPorIndice(int indice);

    void registrarEntrada(List<ItemVenda> itens, Fornecedor fornecedor, double valor, LocalDate vencimento);
}
