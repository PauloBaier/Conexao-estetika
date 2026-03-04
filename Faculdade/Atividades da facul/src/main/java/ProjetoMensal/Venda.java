package ProjetoMensal;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private static int contadorId = 1;

    private int id;
    private Cliente cliente;
    private LocalDateTime data;
    private List<ItemVenda> itens;
    private boolean finalizada;

    public Venda(Cliente cliente){

        if (cliente == null){
            throw new IllegalArgumentException("Cliete não pode ser nulo");
        }

        this.id = contadorId++;
        this.cliente = cliente;
        this.data = LocalDateTime.now();
        this.itens = new ArrayList<>();
        this.finalizada = false;
    }

    public void adicionarItem(ItemVenda item){
        if(finalizada){
            throw new IllegalStateException("Venda ja realizda.");
        }
        itens.add(item);
    }

    public double calcularTotal(){
        double total = 0;

        for (ItemVenda item : itens){
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void finalizarVenda(){

        if (itens.isEmpty()){
            throw new IllegalStateException("Venda sem itens.");
        }
        if (finalizada){
            throw new IllegalStateException("venda já realizada.");
        }
        //remove estouqe apenas finalização
        for (ItemVenda item : itens){
            item.getProduto().removerEstoque(item.getQuantidade());
        }
        finalizada = true;
    }

    @Override
    public String toString(){
        return "Venda ID: " + id +
                "\nCliente: " + cliente.getNome() +
                "\nData: " + data +
                "\nTotal: " + calcularTotal() +
                "\nFinalizada: " + finalizada;
    }
}
