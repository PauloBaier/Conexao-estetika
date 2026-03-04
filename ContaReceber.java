package ProjetoMensal_1;

import java.time.LocalDate;

public class ContaReceber extends Conta {

    private String venda;
    private String cliente;

    public ContaReceber(String id, double valor, LocalDate data,
                        String venda, String cliente) {

        super(id, valor, data);
        setVenda(venda);
        setCliente(cliente);
    }

    // GETTERS

    public String getVenda() {
        return venda;
    }

    public String getCliente() {
        return cliente;
    }

    // SETTERS COM VALIDAÇÃO

    public void setVenda(String venda) {
        if (venda == null || venda.trim().isEmpty()) {
            throw new IllegalArgumentException("Venda não pode ser vazia.");
        }
        this.venda = venda;
    }

    public void setCliente(String cliente) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("Cliente não pode ser vazio.");
        }
        this.cliente = cliente;
    }
}