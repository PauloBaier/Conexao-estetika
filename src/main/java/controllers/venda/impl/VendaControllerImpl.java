package controllers.venda.impl;

import controllers.venda.VendaController;
import controllers.venda.dto.VendaResponse;
import models.Caixa;
import models.Produto;
import models.Usuario;
import models.Venda;
import services.CaixaService;
import services.MovimentacaoCaixaService;
import services.UsuarioService;
import services.VendaService;

import java.util.List;

public class VendaControllerImpl implements VendaController {

    private final VendaService vendaService;
    private final CaixaService caixaService;
    private final MovimentacaoCaixaService movimentacaoCaixaService;
    private final UsuarioService usuarioService;

    public VendaControllerImpl(
            VendaService vendaService,
            CaixaService caixaService,
            MovimentacaoCaixaService movimentacaoCaixaService,
            UsuarioService usuarioService
    ) {
        this.vendaService             = vendaService;
        this.caixaService             = caixaService;
        this.movimentacaoCaixaService = movimentacaoCaixaService;
        this.usuarioService           = usuarioService;
    }

    @Override
    public Venda iniciar(Usuario usuario) {
        return vendaService.iniciar(usuario);
    }

    @Override
    public void cadastrar(Venda venda) {
        vendaService.cadastrar(venda);
    }

    @Override
    public VendaResponse aplicarPagamento(Venda venda, String formaPagamento) {
        vendaService.aplicarPagamento(venda, formaPagamento);
        vendaService.cadastrar(venda);
        return new VendaResponse(
                venda.getId(),
                venda.getStatus().name(),
                venda.getFormaPagamento() != null ? venda.getFormaPagamento().name() : null,
                venda.getValorTotal()
        );
    }

    @Override
    public void cancelar(Venda venda) {
        vendaService.cancelar(venda);
    }

    @Override
    public void adicionarItemNaVenda(Venda venda, Produto produto, int quantidade) {
        vendaService.adicionarItemNaVenda(venda, produto, quantidade);
    }

    @Override
    public void atualizarQuantidadeItem(Venda venda, int linha, int quantidade) {
        vendaService.atualizarQuantidadeItem(venda, linha, quantidade);
    }

    @Override
    public double calcularSubTotal(Venda venda) {
        return vendaService.calcularSubTotal(venda);
    }

    @Override
    public Venda buscar(Long id) {
        return vendaService.buscar(id);
    }

    @Override
    public List<Venda> listar() {
        return vendaService.listar();
    }

    @Override
    public Caixa buscarCaixaAberto() {
        return caixaService.buscarCaixaAberto();
    }

    @Override
    public Caixa abrirCaixaComValor(double valorAbertura, Usuario usuario) {
        return caixaService.abrirCaixaComValor(valorAbertura, usuario);
    }

    @Override
    public void fecharCaixa(Usuario usuario, Venda vendaAtual) {
        caixaService.fecharCaixa(usuario, vendaAtual);
    }

    @Override
    public void registrarSangria(Caixa caixa, double valor, String descricao, Usuario usuario) {
        movimentacaoCaixaService.registrarSangria(caixa, valor, descricao, usuario);
    }

    @Override
    public void registrarSuprimento(Caixa caixa, double valor, String descricao, Usuario usuario) {
        movimentacaoCaixaService.registrarSuprimento(caixa, valor, descricao, usuario);
    }

    @Override
    public Usuario autorizarOperacaoCaixa(String email, String senha) {
        return usuarioService.autorizarOperacaoCaixa(email, senha);
    }
}
