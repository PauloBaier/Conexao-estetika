package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import model.enums.StatusVenda;
import model.enums.FormaPagamento;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "valor_total")
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusVenda status;

    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento")
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "fk_clientes_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens;

    public Venda() {}

    public Venda(Long id, LocalDate data, double valorTotal, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data();
    }

    private LocalDate data() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
        if (itens != null) {
            for (ItemVenda item : itens) {
                item.setVenda(this);
            }
        }
    }

    public StatusVenda getStatus() {
        return status;
    }

    public void setStatus(StatusVenda status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void adicionarItem(Produto produto, int quantidade) {

        if (this.itens == null) {
            this.itens = new java.util.ArrayList<>();
        }

        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(produto.getPrecoVenda());
        item.setTotalItem(produto.getPrecoVenda() * quantidade);
        item.setVenda(this);

        this.itens.add(item);

        this.valorTotal += item.getTotalItem();
    }
}