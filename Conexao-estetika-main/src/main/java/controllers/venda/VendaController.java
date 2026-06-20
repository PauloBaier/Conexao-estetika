package controllers.venda;

import models.Caixa;
import models.Produto;
import models.Usuario;
import models.Venda;

import java.util.List;

import controllers.venda.dto.VendaResponse;

public interface VendaController {

    Venda iniciar(Usuario usuario);
    void cadastrar(Venda venda);
    VendaResponse aplicarPagamento(Venda venda, String formaPagamento);
    void cancelar(Venda venda);
    void adicionarItemNaVenda(Venda venda, Produto produto, int quantidade);
    void atualizarQuantidadeItem(Venda venda, int linha, int quantidade);
    double calcularSubTotal(Venda venda);
    Venda buscar(Long id);
    List<Venda> listar();

    Caixa buscarCaixaAberto();
    Caixa abrirCaixaComValor(double valorAbertura, Usuario usuario);
    void fecharCaixa(Usuario usuario, Venda vendaAtual);
    void registrarSangria(Caixa caixa, double valor, String descricao, Usuario usuario);
    void registrarSuprimento(Caixa caixa, double valor, String descricao, Usuario usuario);
    Usuario autorizarOperacaoCaixa(String email, String senha);
}