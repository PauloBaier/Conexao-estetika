package models;

import jakarta.persistence.*;

@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco_unitario", nullable = false)
    private double precoUnitario;

    @Column(name = "total_item", nullable = false)
    private double totalItem;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "fk_vendas_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "fk_produtos_id", nullable = false)
    private Produto produto;

    public ItemVenda() {
    }

    public ItemVenda(Long id, int quantidade, double precoUnitario, double totalItem, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.totalItem = totalItem;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(double totalItem) {
        this.totalItem = totalItem;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}