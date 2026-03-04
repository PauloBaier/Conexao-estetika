package ProjetoMensal;


public class ItemVenda {
    private Produto produto;
    private int quantidade;

    public ItemVenda(Produto produto, int quantidade){

        if(produto == null){
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (quantidade <= 0){
        throw new IllegalArgumentException("Quantidade deve maior que zero");
        }
        if (quantidade > produto.getQuantidadeEstoque()){
            throw new IllegalArgumentException("Estoque insuficinete");
        }

        this.produto = produto;
        this.quantidade = quantidade;
    }
    public Produto getProduto(){
        return produto;
    }
    public  int getQuantidade(){
        return quantidade;
    }
    public double calcularSubtotal(){
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString(){
        return "Produto: " + produto.getNome() +
                "| Quantidade: " + quantidade +
                "| SubTotal: " + calcularSubtotal();
    }

}
