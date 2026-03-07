package ConexãoEstetika;

public class Produto {  //atributos
    private String id;
    private String nome;
    private String categoria;
    private double precoCusto;
    private double precoVenda;
    private int quantidadeEstoque;
    private int estoqueMinimo;

    Fornecedor fornecedor;

    //constructor
    public Produto(String id, String nome, String categoria, double precoCusto, double precoVenda, int quantidadeEstoque, int estoqueMinimo, Fornecedor fornecedor) {

        if (precoCusto < 0) {
            throw new IllegalArgumentException("Preço de custo não pode ser negativo.");
        }

        if (precoVenda < precoCusto) {
            throw new IllegalArgumentException("Preço de venda deve ser maior ou igual ao preço de custo.");
        }

        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }

        if (estoqueMinimo < 0) {
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo.");
        }

        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
        this.fornecedor = fornecedor;
    }

    //getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoqueMinimo () {
        return estoqueMinimo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        if (precoVenda < this.precoCusto) {
            throw new IllegalArgumentException("Preço de venda deve ser maior ou igual ao preço de custo.");
        }

        this.precoVenda = precoVenda;
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
    public int setEstoqueMinimo() {
        return estoqueMinimo;
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
        this.fornecedor = fornecedor;
    }


    //metodos

    //adicionar estoque
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEstoque += quantidade;
        }
    }

    //remover estoque
    public boolean removerEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantidadeEstoque) {
            this.quantidadeEstoque -= quantidade;
            return true;
        }
        return false;
    }

    //verifica estoque abaixo do minimo
    public String verificarEstoque() {
        if (quantidadeEstoque <= estoqueMinimo) {
            return "ATENÇÃO: Estoque abaixo do mínimo!";
        } else {
            return "Estoque OK.";
        }
    }

}
