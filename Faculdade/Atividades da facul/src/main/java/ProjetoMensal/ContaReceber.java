package ProjetoMensal;

import java.time.LocalDate;

public class ContaReceber extends Conta {
    private Cliente cliente;

    public ContaReceber(String descricao, double valor, LocalDate dataVencimento, Cliente cliente) {
        super(descricao, valor, dataVencimento);
        this.cliente = cliente;
    }
}
