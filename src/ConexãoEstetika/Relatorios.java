package ConexaoEstetika;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Relatorios {

    private Financeiro financeiro;
    private List<Produto> produtos;

    public Relatorios(Financeiro financeiro, List<Produto> produtos) {
        this.financeiro = financeiro;
        this.produtos = produtos;
    }


    // contas a receber

    public List<ContaReceber> listarContasReceber(
            LocalDate dataInicial,
            LocalDate dataFinal,
            FiltroStatus filtro) {

        List<ContaReceber> resultado = new ArrayList<>();

        for (ContaReceber conta : financeiro.getContasReceber()) {

            boolean dentroPeriodo =
                    !conta.getData().isBefore(dataInicial) &&
                            !conta.getData().isAfter(dataFinal);

            boolean statusOk =
                    filtro == FiltroStatus.TODOS ||
                            (filtro == FiltroStatus.PAGO && conta.isPaga()) ||
                            (filtro == FiltroStatus.ABERTO && !conta.isPaga());

            if (dentroPeriodo && statusOk) {
                resultado.add(conta);
            }
        }

        return resultado;
    }


    //contas a pagar

    public List<ContaPagar> listarContasPagar(
            LocalDate dataInicial,
            LocalDate dataFinal,
            FiltroStatus filtro) {

        List<ContaPagar> resultado = new ArrayList<>();

        for (ContaPagar conta : financeiro.getContasPagar()) {

            boolean dentroPeriodo =
                    !conta.getData().isBefore(dataInicial) &&
                            !conta.getData().isAfter(dataFinal);

            boolean statusOk =
                    filtro == FiltroStatus.TODOS ||
                            (filtro == FiltroStatus.PAGO && conta.isPaga()) ||
                            (filtro == FiltroStatus.ABERTO && !conta.isPaga());

            if (dentroPeriodo && statusOk) {
                resultado.add(conta);
            }
        }

        return resultado;
    }


    // produtos

    public List<Produto> listarTodosProdutos() {
        return new ArrayList<>(produtos);
    }

    public List<Produto> listarProdutosEstoqueBaixo() {

        List<Produto> resultado = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produto.getQuantidadeEstoque() <= produto.getEstoqueMinimo()) {
                resultado.add(produto);
            }
        }

        return resultado;
    }
}