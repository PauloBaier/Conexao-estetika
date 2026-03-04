package ProjetoMensal1;

import ProjetoMensal.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private Long id;
    private LocalDateTime data;
    private List<ItemVenda> itens;

    private Cliente cliente;
    private double desconto;
    private StatusVenda status;
    private FormaPagamento formaPagamento;

    public Venda(Long id, Cliente cliente) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id inválido");
        }
        if (cliente == null){
            throw new IllegalArgumentException("Clinete não pode ser null");
        }
        this.id = id;
        this.cliente = cliente;
        this.data = LocalDateTime.now();
        this.desconto = 0;
        this.itens = new ArrayList<>();
        this.status = StatusVenda.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    // Adicionar Item.
    public void adicionarItem(ItemVenda item) {

        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser null!");
        }

        itens.add(item);
    }

    //Remover Item.
    public void removerItem(ItemVenda item) {
        itens.remove(item);
    }

    //Calcular Total da ProjetoMensal1.Venda.
    public double calcularTotal() {

        double total = 0;

        for (ItemVenda item : itens) {
            total += item.calcularSubtotal();
        }

        double valorComDesconto = total - ( total * desconto / 100);

        if (valorComDesconto < 0 ){
            throw new IllegalArgumentException("Desconto não pode zerar o valor total");
        }
        return valorComDesconto;
    }

    public void setDesconto(double desconto){
        if (desconto < 0 ){
            throw new IllegalArgumentException("Desconto não pode ser negativo");
        }
        this.desconto = desconto;
    }
    public void alterarStatus(StatusVenda novoStatus){

        if (this.status == StatusVenda.CANCELADO){
            throw new IllegalArgumentException("ProjetoMensal1.Venda cancelada não alterada");
        }
        this.status = novoStatus;
    }
    public void alterarFormaPagamento(FormaPagamento formaPagamento){
        if (formaPagamento == null ){
            throw new IllegalArgumentException("Forma de pagamento naõ pode ser null");
        }
        this.formaPagamento = formaPagamento;
    }
    public void finalizarVenda(){


        if (itens.isEmpty()){
            throw new IllegalStateException("ProjetoMensal1.Venda não possui itens");
        }
        if (status == StatusVenda.CANCELADO){
            throw new IllegalStateException("ProjetoMensal1.Venda cancelada não finalizada");
        }
        for(ItemVenda item : itens ){
            item.getProduto().removerEstoque(item.getQuantidade());
        }
        this.status = StatusVenda.PAGO;
    }
