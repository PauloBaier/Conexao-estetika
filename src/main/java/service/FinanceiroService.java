package service;

import model.*;
import model.enums.*;

import java.time.LocalDate;

public class FinanceiroService {

    private ContaReceberService contaReceberService;
    private ContaPagarService contaPagarService;
    private MovimentacaoCaixaService movimentacaoService;

    public FinanceiroService(ContaReceberService contaReceberService, ContaPagarService contaPagarService, MovimentacaoCaixaService movimentacaoService) {
        this.contaReceberService = contaReceberService;
        this.contaPagarService = contaPagarService;
        this.movimentacaoService = movimentacaoService;
    }

    public void receberConta(ContaReceber conta, Caixa caixa) {

        if (conta == null || caixa == null) {
            throw new IllegalArgumentException("Conta ou caixa inválido.");
        }

        conta.setStatus(StatusConta.PAGO);
        conta.setDataPagamento(LocalDate.now());

        contaReceberService.atualizar(conta);

        MovimentacaoCaixa mov = new MovimentacaoCaixa();
        mov.setCaixa(caixa);
        mov.setTipo(TipoMovimento.ENTRADA);
        mov.setValor(conta.getValor());
        mov.setDescricao("Recebimento de conta");

        movimentacaoService.registrarMovimentacao(mov);
    }

    public void pagarConta(ContaPagar conta, Caixa caixa) {

        if (conta == null || caixa == null) {
            throw new IllegalArgumentException("Conta ou caixa inválido.");
        }

        conta.setStatus(StatusConta.PAGO);
        conta.setDataPagamento(LocalDate.now());

        contaPagarService.atualizar(conta);

        MovimentacaoCaixa mov = new MovimentacaoCaixa();
        mov.setCaixa(caixa);
        mov.setTipo(TipoMovimento.SAIDA);
        mov.setValor(conta.getValor());
        mov.setDescricao("Pagamento de conta");

        movimentacaoService.registrarMovimentacao(mov);
    }
}
