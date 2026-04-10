package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import model.enums.StatusCaixa;

@Entity
@Table(name = "caixa")
public class Caixa {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_abertura")
    private LocalDate dataAbertura;

    @Column(name = "valor_abertura")
    private double valorAbertura;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCaixa status;


    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL)
    private List<MovimentacaoCaixa> movimentacoes;

    @ManyToOne
    @JoinColumn(name = "fk_vendas_id")
    private Venda venda;

    public Caixa() {
    }

    public Caixa(Long id, LocalDate dataAbertura, double valorAbertura, LocalDate dataFechamento, StatusCaixa status, List<MovimentacaoCaixa> movimentacoes) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.valorAbertura = valorAbertura;
        this.dataFechamento = dataFechamento;
        this.status = status;
        this.movimentacoes = movimentacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(double valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }


    public StatusCaixa getStatus() {
        return status;
    }

    public void setStatus(StatusCaixa status) {
        this.status = status;
    }

    public List<MovimentacaoCaixa> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoCaixa> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }


}