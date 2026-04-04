package org.conexaoestetika.models;


import org.conexaoestetika.models.enums.FormaPagamento;
import org.conexaoestetika.models.enums.StatusVenda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int id;
    private LocalDateTime data;
    private List<ItemVenda> itens;

    private Cliente cliente;
    private double desconto;
    private StatusVenda status;
    private FormaPagamento formaPagamento;

    public Venda(int id, Cliente cliente) {

        if (id <= 0) {
            throw new IllegalArgumentException("Id inválido");
        }
        this.id = id;
        this.cliente = cliente;
        this.data = LocalDateTime.now();
        this.desconto = 0;
        this.itens = new ArrayList<>();
        this.status = StatusVenda.PENDENTE;
    }

    public StatusVenda getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    // Adicionar Item.
    public void adicionarItem(ItemVenda item) {

        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser null!");
        }

        itens.add(item);
        calcularTotal();
    }

    //Remover Item.
    public void removerItem(ItemVenda item) {
        itens.remove(item);
        calcularTotal();
    }

    //Calcular Total da ProjetoMensal1.Venda.
    public double calcularTotal() {

        double total = 0;

        for (ItemVenda item : itens) {
            total += item.calcularSubtotal();
        }

        double valorComDesconto = total - (total * desconto / 100);

        if (valorComDesconto < 0) {
            throw new IllegalArgumentException("Desconto não pode zerar o valor total");
        }
        return valorComDesconto;
    }

    public void setDesconto(double desconto) {
        if (desconto < 0) {
            throw new IllegalArgumentException("Desconto não pode ser negativo");
        }
        this.desconto = desconto;
        this.calcularTotal();
    }

    public void alterarStatus(StatusVenda novoStatus) {

        if (this.status == StatusVenda.CANCELADO) {
            throw new IllegalArgumentException("ProjetoMensal1.Venda cancelada não alterada");
        }
        this.status = novoStatus;
    }

    public void alterarFormaPagamento(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento naõ pode ser null");
        }
        this.formaPagamento = formaPagamento;

    }


}
