package Pedido;

import java.util.ArrayList;

public class Carrinho {
    private int numeroPedido;
    private  Usuario cliente;
    private Restaurante restaurante;
    private ArrayList<Produto> itens;
    private  double valorTotal;


    private  Carrinho(int numeroPedido, Usuario cliente, Restaurante restaurante){
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.itens = new ArrayList<Produto>();
        this.valorTotal = 0;
    }

    public void  adicionarItem(Produto p){
        this.itens.add(p);
        this.calcularTotal();

    }

    private  void calcularTotal(){
        double somaTotal = 0;
        for(Produto p : this.itens){
            somaTotal += p.getPreco();
        }
        this.valorTotal = somaTotal;
    }


    public double getValorTotal() {
        return valorTotal;
    }


}
