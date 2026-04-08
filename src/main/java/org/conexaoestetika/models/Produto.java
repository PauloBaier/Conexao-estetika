package org.conexaoestetika.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import org.conexaoestetika.repositories.FornecedorRepository;

import java.util.List;

@Entity
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
    @JoinTable(name = "produto_fornecedor",
        joinColumns = @JoinColumn(name = "fk_produto_id"),
        inverseJoinColumns = @JoinColumn(name = "fk_fornecedor_id")
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("ERRO: Nome de Produto não pode ser nulo ou vazio!");
        }
        this.nome = nome;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        if(precoCompra < 0.0){
            throw  new IllegalArgumentException("ERRO: Preço de Compra não pode ser negativo!");
        }
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        if(precoVenda < this.getPrecoCompra()){
            throw new IllegalArgumentException("ERRO: Preço de Venda deve ser maior que o preço de Compra!");
        }
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if(quantidadeEstoque < 0){
            throw new IllegalArgumentException("ERRO: Estoque deve ser um número positvo!");
        }
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        if(estoqueMinimo < 0){
            throw new IllegalArgumentException("ERRO: Estoque Mínimo deve ser um número positvo!");
        }
        this.estoqueMinimo = estoqueMinimo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if(categoria == null){
            throw new IllegalArgumentException("ERRO: Categoria não pode ser nulo!");
        }
        this.categoria = categoria;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        if(fornecedores == null || fornecedores.isEmpty() ){
            throw new IllegalArgumentException("ERRO: Fornecedores não pode estar vazio ou ser nulo!");
        }
        this.fornecedores = fornecedores;
    }

    public void adicionarFornecedor(Fornecedor fornecedor){
        if(fornecedor == null){
            throw new IllegalArgumentException("ERRO: Fornecedor não pode ser nulo!");
        }
        if(!this.fornecedores.contains(fornecedor)){
            this.fornecedores.add(fornecedor);
        }
    }

    public void removerFornecedor(Fornecedor fornecedor){
        if(fornecedores == null || fornecedores.isEmpty() ){
            throw new IllegalArgumentException("ERRO: Fornecedores não pode estar vazio ou ser nulo!");
        }
        if(this.fornecedores.size() == 1){
            throw new IllegalArgumentException("ERRO: Fornecedores deve manter pelo menos um registro!");
        }
        if(this.fornecedores.contains(fornecedor)){
            this.fornecedores.remove(fornecedor);
        }
    }
}
