package ProjetoMensal_1;

import java.time.LocalDate;

public class ContaPagar extends Conta {

    private String itens;
    private String fornecedor;
    private LocalDate vencimento;

    public ContaPagar(String id, double valor, LocalDate data,
                      String itens, String fornecedor, LocalDate vencimento) {

        super(id, valor, data);
        setItens(itens);
        setFornecedor(fornecedor);
        setVencimento(vencimento);
    }


    public String getItens() {
        return itens;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }


    public void setItens(String itens) {
        if (itens == null || itens.trim().isEmpty()) {
            throw new IllegalArgumentException("Itens não podem ser vazios.");
        }
        this.itens = itens;
    }

    public void setFornecedor(String fornecedor) {
        if (fornecedor == null || fornecedor.trim().isEmpty()) {
            throw new IllegalArgumentException("Fornecedor não pode ser vazio.");
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