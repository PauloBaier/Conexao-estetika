package models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "preco_compra", nullable = false)
    private double precoCompra;

    @Column(name = "preco_venda", nullable = false)
    private double precoVenda;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    @Column(name = "estoque_minimo", nullable = false)
    private int estoqueMinimo;

    @ManyToOne
    @JoinColumn(name = "fk_categorias_id", nullable = false)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "produtos_fornecedores",
            joinColumns = @JoinColumn(name = "fk_produtos_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_fornecedores_id")
    )
    private List<Fornecedor> fornecedores = new ArrayList<>();

    public Produto() {
    }

    public Produto(Long id, String nome, double precoCompra, double precoVenda,
                   int quantidadeEstoque, int estoqueMinimo,
                   Categoria categoria, List<Fornecedor> fornecedores) {
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
        this.categoria = categoria;
        this.fornecedores = (fornecedores != null) ? fornecedores : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = (fornecedores != null) ? fornecedores : new ArrayList<>();
    }

    public void adicionarFornecedor(Fornecedor fornecedor) {
        if (fornecedor != null && !this.fornecedores.contains(fornecedor)) {
            this.fornecedores.add(fornecedor);
        }
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        if (fornecedor != null) {
            this.fornecedores.remove(fornecedor);
        }
    }
}