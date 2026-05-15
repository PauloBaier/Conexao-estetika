package models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import models.enums.StatusCaixa;

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

    @Column(name = "saldo_atual")
    private double saldoAtual;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCaixa status;

    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL)
    private List<MovimentacaoCaixa> movimentacoes;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id", nullable = false)
    private Usuario usuario;

    public Caixa() {
    }

    public Caixa(Long id, LocalDate dataAbertura, double valorAbertura, double saldoAtual,
                 LocalDate dataFechamento, StatusCaixa status, List<MovimentacaoCaixa> movimentacoes, Usuario usuario) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.valorAbertura = valorAbertura;
        this.saldoAtual = saldoAtual;
        this.dataFechamento = dataFechamento;
        this.status = status;
        this.movimentacoes = movimentacoes;
        this.usuario = usuario;
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

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}