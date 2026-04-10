package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import repository.FornecedorRepository;

import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {  //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "preco_compra")
    private double precoCompra;
    @Column(name = "preco_venda")
    private double precoVenda;
    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;
    @Column(name = "estoque_minimo")
    private int estoqueMinimo;
    @ManyToOne
    @JoinColumn(name = "fk_categorias_id")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(name = "produtos_fornecedores",
            joinColumns = @JoinColumn(name = "fk_produtos_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_fornecedores_id")
    )
    private List<Fornecedor> fornecedores;

    public Produto(){};

    //constructor
    public Produto(String nome, double precoCompra, double precoVenda, int quantidadeEstoque, int estoqueMinimo, Categoria categoria, List<Fornecedor>fornecedores) {
        this.setNome(nome);
        this.setPrecoCompra(precoCompra);
        this.setPrecoVenda(precoVenda);
        this.setQuantidadeEstoque(quantidadeEstoque);
        this.setEstoqueMinimo(estoqueMinimo);
        this.setCategoria(categoria);
        this.setFornecedores(fornecedores);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        this.fornecedores = fornecedores;
    }

    public void adicionarFornecedor(Fornecedor fornecedor) {
        this.fornecedores = fornecedores;
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        this.fornecedores = fornecedores;
    }
}
