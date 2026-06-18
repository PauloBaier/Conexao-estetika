package controllers.relatorio.impl;

import controllers.relatorio.RelatorioController;
import controllers.relatorio.dto.*;
import services.ProdutoService;
import services.RelatorioLocal;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioControllerImpl implements RelatorioController {

    private final RelatorioLocal relatorioLocal;
    private final ProdutoService produtoService;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioControllerImpl(RelatorioLocal relatorioLocal, ProdutoService produtoService) {
        this.relatorioLocal = relatorioLocal;
        this.produtoService = produtoService;
    }

    @Override
    public List<ContaReceberRelatorioResponse> listarContasReceber() {
        return relatorioLocal.contasReceber(null, null, null).stream()
                .map(c -> new ContaReceberRelatorioResponse(
                        c.getId(),
                        c.getCliente() != null ? c.getCliente().getNome() : "Sem cliente",
                        c.getDescricao(),
                        c.getDataEmissao()    != null ? c.getDataEmissao().format(FMT)    : "-",
                        c.getDataVencimento() != null ? c.getDataVencimento().format(FMT) : "-",
                        c.getDataPagamento()  != null ? c.getDataPagamento().format(FMT)  : "-",
                        c.getValor(),
                        c.getStatus().name()
                )).toList();
    }

    @Override
    public List<ContaPagarRelatorioResponse> listarContasPagar() {
        return relatorioLocal.contasPagar(null, null, null).stream()
                .map(c -> new ContaPagarRelatorioResponse(
                        c.getId(),
                        c.getFornecedor() != null ? c.getFornecedor().getNome() : "-",
                        c.getDescricao(),
                        c.getTipoDespesas(),
                        c.getDataEmissao()    != null ? c.getDataEmissao().format(FMT)    : "-",
                        c.getDataVencimento() != null ? c.getDataVencimento().format(FMT) : "-",
                        c.getValor(),
                        c.getStatus().name()
                )).toList();
    }

    @Override
    public List<ContaPagarVencidaResponse> listarContasVencidas() {
        return relatorioLocal.contasPagarVencidas().stream()
                .map(c -> new ContaPagarVencidaResponse(
                        c.getId(),
                        c.getFornecedor() != null ? c.getFornecedor().getNome() : "-",
                        c.getDescricao(),
                        c.getDataEmissao().format(FMT),
                        c.getDataVencimento().format(FMT),
                        relatorioLocal.diasEmAtraso(c),
                        c.getValor(),
                        c.getStatus().name()
                )).toList();
    }

    @Override
    public List<ProdutoRelatorioResponse> listarEstoqueBaixo() {
        return relatorioLocal.produtosEstoqueBaixo().stream()
                .map(p -> new ProdutoRelatorioResponse(
                        p.getId(), p.getNome(),
                        p.getCategoria() != null ? p.getCategoria().getNome() : "-",
                        p.getPrecoCompra(), p.getPrecoVenda(),
                        p.getQuantidadeEstoque(), p.getEstoqueMinimo()
                )).toList();
    }

    @Override
    public List<ProdutoRelatorioResponse> listarTodosProdutos() {
        return produtoService.listarTodos().stream()
                .map(p -> new ProdutoRelatorioResponse(
                        p.getId(), p.getNome(),
                        p.getCategoria() != null ? p.getCategoria().getNome() : "-",
                        p.getPrecoCompra(), p.getPrecoVenda(),
                        p.getQuantidadeEstoque(), p.getEstoqueMinimo()
                )).toList();
    }
}
