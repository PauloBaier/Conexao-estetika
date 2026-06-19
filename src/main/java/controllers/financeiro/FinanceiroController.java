package controllers.financeiro;

import controllers.financeiro.dto.ContaPagarResponse;
import controllers.financeiro.dto.ContaReceberResponse;
import models.ContaPagar;
import models.ContaReceber;
import models.Usuario;

import java.util.List;

public interface FinanceiroController {

    List<ContaPagarResponse> buscarTodasContasPagar();
    ContaPagar buscarContaPagar(Long id);
    void pagarConta(ContaPagar conta, Usuario usuario);

    List<ContaReceberResponse> buscarTodasContasReceber();
    ContaReceber buscarContaReceber(Long id);
    void receberConta(ContaReceber conta, Usuario usuario);
}