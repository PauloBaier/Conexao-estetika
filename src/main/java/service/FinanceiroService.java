package service;

import model.*;
import model.enums.*;
import java.time.LocalDate;



public class FinanceiroService {

    private ContaReceberService contaReceberService = new ContaReceberService();
    private ContaPagarService contaPagarService = new ContaPagarService();
    private MovimentacaoCaixaService movimentacaoService = new MovimentacaoCaixaService();


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

        movimentacaoService.registrarMovimentacao(mov);;
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
