package services;

import models.Caixa;
import models.ContaPagar;
import models.ContaReceber;
import models.MovimentacaoCaixa;
import models.Usuario;
import models.enums.StatusCaixa;
import models.enums.StatusConta;
import models.enums.TipoMovimento;

import java.time.LocalDate;

public class FinanceiroService {

    private final ContaReceberService contaReceberService;
    private final ContaPagarService contaPagarService;
    private final MovimentacaoCaixaService movimentacaoService;

    public FinanceiroService(
            ContaReceberService contaReceberService,
            ContaPagarService contaPagarService,
            MovimentacaoCaixaService movimentacaoService
    ) {
        this.contaReceberService = contaReceberService;
        this.contaPagarService = contaPagarService;
        this.movimentacaoService = movimentacaoService;
    }

    public void receberConta(ContaReceber conta, Caixa caixa, Usuario usuario) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta a receber inválida.");
        }

        if (caixa == null) {
            throw new IllegalArgumentException("Caixa inválido.");
        }

        if (caixa.getStatus() != StatusCaixa.ABERTO) {
            throw new IllegalArgumentException("O caixa precisa estar aberto para receber conta.");
        }

        if (conta.getStatus() == StatusConta.PAGO) {
            throw new IllegalArgumentException("Essa conta já foi recebida.");
        }

        conta.setStatus(StatusConta.PAGO);
        conta.setDataPagamento(LocalDate.now());
        contaReceberService.atualizar(conta);

        MovimentacaoCaixa mov = new MovimentacaoCaixa();
        mov.setCaixa(caixa);
        mov.setTipo(TipoMovimento.ENTRADA);
        mov.setValor(conta.getValor());
        mov.setDescricao("Recebimento de conta ID " + conta.getId());

        movimentacaoService.registrarMovimentacao(mov, usuario);
    }

    public void pagarConta(ContaPagar conta, Caixa caixa, Usuario usuario) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta a pagar inválida.");
        }

        if (caixa == null) {
            throw new IllegalArgumentException("Caixa inválido.");
        }

        if (caixa.getStatus() != StatusCaixa.ABERTO) {
            throw new IllegalArgumentException("O caixa precisa estar aberto para pagar conta.");
        }

        if (conta.getStatus() == StatusConta.PAGO) {
            throw new IllegalArgumentException("Essa conta já foi paga.");
        }

        if (caixa.getSaldoAtual() < conta.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente no caixa para pagar essa conta.");
        }

        conta.setStatus(StatusConta.PAGO);
        conta.setDataPagamento(LocalDate.now());
        contaPagarService.atualizar(conta);

        MovimentacaoCaixa mov = new MovimentacaoCaixa();
        mov.setCaixa(caixa);
        mov.setTipo(TipoMovimento.SAIDA);
        mov.setValor(conta.getValor());
        mov.setDescricao("Pagamento de conta ID " + conta.getId());

        movimentacaoService.registrarMovimentacao(mov, usuario);
    }
}