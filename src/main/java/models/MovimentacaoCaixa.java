package models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import models.enums.TipoMovimento;

@Entity
@Table(name = "movimentacao_caixa")
public class MovimentacaoCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_caixa_id", nullable = false)
    private Caixa caixa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMovimento tipo;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id", nullable = false)
    private Usuario usuario;

    public MovimentacaoCaixa() {
    }

    public MovimentacaoCaixa(Long id, Caixa caixa, TipoMovimento tipo, double valor,
                             String descricao, LocalDateTime dataMovimentacao, Usuario usuario) {
        this.id = id;
        this.caixa = caixa;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataMovimentacao = dataMovimentacao;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}