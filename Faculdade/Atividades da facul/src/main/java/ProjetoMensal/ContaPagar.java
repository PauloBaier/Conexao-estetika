package ProjetoMensal;

import java.time.LocalDate;

public class ContaPagar extends Conta {

    private Fornecedor fornecedor;

    public ContaPagar(String descricao, double valor, LocalDate dataVencimento, Fornecedor fornecedor) {
        super(descricao, valor, dataVencimento);
        this.fornecedor = fornecedor;
    }
}
