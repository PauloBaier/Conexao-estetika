package org.conexaoestetika;

import java.time.LocalDate;

public class ContaReceber extends Conta {

    private Venda venda;
    private Cliente cliente;

    public ContaReceber(int id, double valor, LocalDate data, Venda venda, Cliente cliente) {

        super(id, valor, data);
        setVenda(venda);
        setCliente(cliente);
    }


    public Venda getVenda() {
        return venda;
    }

    public Cliente getCliente() {
        return cliente;
    }


public void setVenda(Venda venda) {
    if (venda == null) {
        throw new IllegalArgumentException("Venda não pode ser nula.");
    }
    this.venda = venda;
}

public void setCliente(Cliente cliente) {
    if (cliente == null) {
        throw new IllegalArgumentException("Cliente não pode ser nulo.");
    }
    this.cliente = cliente;
    }
}
