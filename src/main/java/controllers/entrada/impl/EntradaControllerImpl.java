package controllers.entrada.impl;

import controllers.entrada.EntradaController;
import controllers.entrada.dto.FornecedorEntradaResponse;
import controllers.entrada.dto.ProdutoEntradaResponse;
import models.Fornecedor;
import models.ItemVenda;
import models.Produto;
import services.EntradaEstoqueService;
import services.FornecedorService;
import services.ProdutoService;

import java.time.LocalDate;
import java.util.List;

public class EntradaControllerImpl implements EntradaController {

    private final ProdutoService       produtoService;
    private final FornecedorService    fornecedorService;
    private final EntradaEstoqueService entradaEstoqueService;

    private List<Fornecedor> fornecedoresCache;
    private List<Produto>    produtosCache;

    public EntradaControllerImpl(
            ProdutoService produtoService,
            FornecedorService fornecedorService,
            EntradaEstoqueService entradaEstoqueService
    ) {
        this.produtoService        = produtoService;
        this.fornecedorService     = fornecedorService;
        this.entradaEstoqueService = entradaEstoqueService;
    }

    @Override
    public List<FornecedorEntradaResponse> listarFornecedores() {
        fornecedoresCache = fornecedorService.listarTodos();
        return fornecedoresCache.stream()
                .map(f -> new FornecedorEntradaResponse(f.getId(), f.getNome()))
                .toList();
    }

    @Override
    public Fornecedor buscarFornecedorPorIndice(int indice) {
        if (fornecedoresCache == null) fornecedorService.listarTodos();
        return fornecedoresCache.get(indice);
    }

    @Override
    public List<ProdutoEntradaResponse> listarProdutos() {
        produtosCache = produtoService.listarTodos();
        return produtosCache.stream()
                .map(p -> new ProdutoEntradaResponse(p.getId(), p.getNome()))
                .toList();
    }

    @Override
    public Produto buscarProdutoPorIndice(int indice) {
        if (produtosCache == null) produtoService.listarTodos();
        return produtosCache.get(indice);
    }

    @Override
    public void registrarEntrada(List<ItemVenda> itens, Fornecedor fornecedor, double valor, LocalDate vencimento) {
        entradaEstoqueService.registrarEntradaEstoque(itens, fornecedor, valor, vencimento);
    }
}