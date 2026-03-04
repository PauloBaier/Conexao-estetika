package ProjetoMensal_1;
import java.util.List;
import ProjetoMensal_1.Fornecedor;
import ProjetoMensal_1.ItemVenda;

import java.time.LocalDate;

public class ContaPagar extends Conta {

    private Fornecedor fornecedor;
    private List<ItemVenda> itens;
    private LocalDate vencimento;

    public ContaPagar(int id, double valor, LocalDate data, List<ItemVenda> itens, Fornecedor fornecedor, LocalDate vencimento) {

        super(id, valor, data);
        setItens(itens);
        setFornecedor(fornecedor);
        setVencimento(vencimento);
    }


    public List<ItemVenda> getItens() {
        return itens;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }


public void setItens(List<ItemVenda> itens) {
    if (itens == null || itens.isEmpty()) {
        throw new IllegalArgumentException("Lista de itens não pode ser vazia.");
    }
    this.itens = itens;
}
public void setFornecedor(Fornecedor fornecedor) {
    if (fornecedor == null) {
        throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
    }
    this.fornecedor = fornecedor;
}

 public void setVencimento(LocalDate vencimento) {
        if (vencimento == null) {
            throw new IllegalArgumentException("Vencimento não pode ser nulo.");
        }
        if (vencimento.isBefore(getData())) {
            throw new IllegalArgumentException("Vencimento não pode ser antes da data da conta.");
        }
        this.vencimento = vencimento;
    }
}
