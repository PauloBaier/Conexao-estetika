package ProjetoMensal;

public class Produto {

    private static int contadorId = 1;

    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String nome, double preco, int quantidadeEstoque){

        if (preco < 0 ){
            throw new IllegalArgumentException("Preco não pode negativo! ");
        }
        if (quantidadeEstoque < 0 ){
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.id = contadorId++;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;

    }
    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public double getPreco(){
        return preco;
    }
    public  int getQuantidadeEstoque(){
        return quantidadeEstoque;
    }
    public void adicionarEstoque(int quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade Invalida");
        }
        this.quantidadeEstoque += quantidade;
    }
    public void removerEstoque(int quantidade){
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quatidad invalida");
        }
        if (quantidade > this.quantidadeEstoque){
            throw new IllegalArgumentException("Estoque insuficiente");
        }
        this.quantidadeEstoque -= quantidade;
    }
    @Override
    public String toString(){
        return "Id: " + id +
                " | Nome: " + nome +
                " | Preço: " + preco +
                " | Estoque " + quantidadeEstoque;

    }

}
