package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import model.enums.TipoMovimento;

@Entity
@Table(name = "movimentacao_caixa")

public class MovimentacaoCaixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_caixa_id")
    private Caixa caixa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoMovimento tipo;

    private double valor;
    private String descricao;

    @Column(name = "data_movimentacao")
    private LocalDateTime dataMovimentacao;

    public MovimentacaoCaixa() {}

    public MovimentacaoCaixa(Long id, Caixa caixa, TipoMovimento tipo, double valor, String descricao, LocalDateTime dataMovimentacao) {
        this.id = id;
        this.caixa = caixa;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataMovimentacao = dataMovimentacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public TipoMovimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimento tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
}
