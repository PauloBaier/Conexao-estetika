package controllers.relatorio;

import controllers.relatorio.dto.*;

import java.util.List;

public interface RelatorioController {

    List<ContaReceberRelatorioResponse> listarContasReceber();
    List<ContaPagarRelatorioResponse>   listarContasPagar();
    List<ContaPagarVencidaResponse>     listarContasVencidas();
    List<ProdutoRelatorioResponse>      listarEstoqueBaixo();
    List<ProdutoRelatorioResponse>      listarTodosProdutos();
}
