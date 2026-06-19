package controllers.financeiro.impl;

import controllers.financeiro.FinanceiroController;
import controllers.financeiro.dto.ContaPagarResponse;
import controllers.financeiro.dto.ContaReceberResponse;
import models.ContaPagar;
import models.ContaReceber;
import models.Usuario;
import services.ContaPagarService;
import services.ContaReceberService;
import services.FinanceiroService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class FinanceiroControllerImpl implements FinanceiroController {

    private final ContaPagarService   contaPagarService;
    private final ContaReceberService contaReceberService;
    private final FinanceiroService   financeiroService;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FinanceiroControllerImpl(
            ContaPagarService contaPagarService,
            ContaReceberService contaReceberService,
            FinanceiroService financeiroService
    ) {
        this.contaPagarService   = contaPagarService;
        this.contaReceberService = contaReceberService;
        this.financeiroService   = financeiroService;
    }

    @Override
    public List<ContaPagarResponse> buscarTodasContasPagar() {
        return contaPagarService.listar().stream()
                .map(c -> ContaPagarResponse.of(c, FMT))
                .toList();
    }

    @Override
    public ContaPagar buscarContaPagar(Long id) {
        return contaPagarService.buscar(id);
    }

    @Override
    public void pagarConta(ContaPagar conta, Usuario usuario) {
        financeiroService.pagarConta(conta, usuario);
    }

    @Override
    public List<ContaReceberResponse> buscarTodasContasReceber() {
        return contaReceberService.listar().stream()
                .map(c -> ContaReceberResponse.of(c, FMT))
                .toList();
    }

    @Override
    public ContaReceber buscarContaReceber(Long id) {
        return contaReceberService.buscar(id);
    }

    @Override
    public void receberConta(ContaReceber conta, Usuario usuario) {
        financeiroService.receberConta(conta, usuario);
    }
}