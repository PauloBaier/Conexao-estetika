package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import model.enums.StatusCaixa;

@Entity
@Table(name = "caixa")
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataAbertura;
    private double valorAbertura;
    private LocalDateTime dataFechamento;
    private double valorFechamento;

    @Enumerated(EnumType.STRING)
    private StatusCaixa status;

    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL)
    private List<MovimentacaoCaixa> movimentacoes;

    public Caixa() {
    }

    public Caixa(Long id, LocalDateTime dataAbertura, double valorAbertura, LocalDateTime dataFechamento, double valorFechamento, StatusCaixa status, List<MovimentacaoCaixa> movimentacoes) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.valorAbertura = valorAbertura;
        this.dataFechamento = dataFechamento;
        this.valorFechamento = valorFechamento;
        this.status = status;
        this.movimentacoes = movimentacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(double valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public double getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(double valorFechamento) {
        this.valorFechamento = valorFechamento;
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
