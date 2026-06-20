package models;

import jakarta.persistence.*;
import java.time.LocalDate;
import models.enums.StatusConta;

@Entity
@Table(name = "contas_receber")
public class ContaReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusConta status;

    @ManyToOne
    @JoinColumn(name = "fk_clientes_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_vendas_id", nullable = false)
    private Venda venda;

    public ContaReceber() {
    }

    public ContaReceber(Long id, String descricao, double valor, LocalDate dataEmissao,
                        LocalDate dataVencimento, LocalDate dataPagamento,
                        StatusConta status, Cliente cliente, Venda venda) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.cliente = cliente;
        this.venda = venda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public StatusConta getStatus() {
        return status;
    }

    public void setStatus(StatusConta status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}