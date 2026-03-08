package org.conexaoestetika;

public class Produto implements IIdentificador {  //atributos
    private int id;
    private String nome;
    private String categoria;
    private double precoVenda;
    private double precoCusto;
    private int quantidadeEstoque;
    private int estoqueMinimo;

    private Fornecedor fornecedor;

    //constructor
    public Produto(int id, String nome, String categoria,
                   double precoCusto, double precoVenda,
                   int quantidadeEstoque, int estoqueMinimo,
                   Fornecedor fornecedor) {


        this.setId(id);
        this.setNome(nome);
        this.setCategoria(categoria);
        this.setPrecoVenda(precoVenda);
        this.setPrecoCusto(precoCusto);
        this.setQuantidadeEstoque(quantidadeEstoque);
        this.setEstoqueMinimo(estoqueMinimo);
        this.setFornecedor(fornecedor);
    }

    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que 0.");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }
        this.nome = nome;
    }

    public int getEstoqueMinimo () {
        return estoqueMinimo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser vazia.");
        }

        this.categoria = categoria;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {

        if (precoVenda < 0) {
            throw new IllegalArgumentException("Preço de venda não pode ser negativo.");
        }

        if (precoVenda < this.precoCusto) {
            throw new IllegalArgumentException("Preço de venda deve ser maior ou igual ao preço de custo.");
        }

        this.precoVenda = precoVenda;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        if (precoCusto < 0) {
            throw new IllegalArgumentException("Preço de custo não pode ser negativo.");

        }

        if (this.precoVenda < precoCusto) {
            throw new IllegalArgumentException("Preço de custo não pode ser maior que o preço de venda atual.");
        }

        this.precoCusto = precoCusto;
    }


    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }

        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        if (estoqueMinimo < 0) {
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo.");
        }

        this.estoqueMinimo = estoqueMinimo;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
        }

        this.fornecedor = fornecedor;
    }


    //metodos

    //adicionar estoque
    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        this.quantidadeEstoque += quantidade;
    }

    //remover estoque
    public boolean removerEstoque(int quantidade) {

        if (quantidade <= 0) {
            return false;
        }

        if (quantidade > quantidadeEstoque) {
            return false;
        }

        quantidadeEstoque -= quantidade;
        return true;
    }

    // verifica se o estoque está abaixo do mínimo
    public boolean precisaReporEstoque() {
        return quantidadeEstoque <= estoqueMinimo;
    }

}
