package Pedido;

public class Produto {
    private String nome;
    private double preco;
    private String desgricao;
    private String cattegoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDesgricao() {
        return desgricao;
    }

    public void setDesgricao(String desgricao) {
        this.desgricao = desgricao;
    }

    public String getCattegoria() {
        return cattegoria;
    }

    public void setCattegoria(String cattegoria) {
        this.cattegoria = cattegoria;
    }
}
