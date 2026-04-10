package org.conexaoestetika.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataRelatorio;
    private double totalEntradaCaixa;
    private double totalSaidaCaixa;
    private double saldoCaixa;
    private double totalVendas;
    private int numeroVendas;
    private int totalClientes;
    private int totalFornecedores;
    private int totalProdutos;
    private int quantidadeEstoqueTotal;
    private double valorEstoqueTotal;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "caixa_id")
    private Caixa caixa;


    public Relatorio() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataRelatorio() { return dataRelatorio; }
    public void setDataRelatorio(LocalDateTime dataRelatorio) { this.dataRelatorio = dataRelatorio; }

    public double getTotalEntradaCaixa() { return totalEntradaCaixa; }
    public void setTotalEntradaCaixa(double totalEntradaCaixa) { this.totalEntradaCaixa = totalEntradaCaixa; }

    public double getTotalSaidaCaixa() { return totalSaidaCaixa; }
    public void setTotalSaidaCaixa(double totalSaidaCaixa) { this.totalSaidaCaixa = totalSaidaCaixa; }

    public double getSaldoCaixa() { return saldoCaixa; }
    public void setSaldoCaixa(double saldoCaixa) { this.saldoCaixa = saldoCaixa; }

    public double getTotalVendas() { return totalVendas; }
    public void setTotalVendas(double totalVendas) { this.totalVendas = totalVendas; }

    public int getNumeroVendas() { return numeroVendas; }
    public void setNumeroVendas(int numeroVendas) { this.numeroVendas = numeroVendas; }

    public int getTotalClientes() { return totalClientes; }
    public void setTotalClientes(int totalClientes) { this.totalClientes = totalClientes; }

    public int getTotalFornecedores() { return totalFornecedores; }
    public void setTotalFornecedores(int totalFornecedores) { this.totalFornecedores = totalFornecedores; }

    public int getTotalProdutos() { return totalProdutos; }
    public void setTotalProdutos(int totalProdutos) { this.totalProdutos = totalProdutos; }

    public int getQuantidadeEstoqueTotal() { return quantidadeEstoqueTotal; }
    public void setQuantidadeEstoqueTotal(int quantidadeEstoqueTotal) { this.quantidadeEstoqueTotal = quantidadeEstoqueTotal; }

    public double getValorEstoqueTotal() { return valorEstoqueTotal; }
    public void setValorEstoqueTotal(double valorEstoqueTotal) { this.valorEstoqueTotal = valorEstoqueTotal; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public Caixa getCaixa() { return caixa; }
    public void setCaixa(Caixa caixa) { this.caixa = caixa; }
}