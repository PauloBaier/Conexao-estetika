package models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.enums.StatusVenda;
import models.enums.FormaPagamento;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusVenda status;

    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento", length = 20)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "fk_clientes_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id", nullable = false)
    private Usuario usuario;

    public Venda() {
    }

    public Venda(Long id, LocalDate data, double valorTotal, StatusVenda status,
                 FormaPagamento formaPagamento, Cliente cliente, List<ItemVenda> itens,  Usuario usuario) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.cliente = cliente;
        this.itens = (itens != null) ? itens : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
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
        this.itens = (itens != null) ? itens : new ArrayList<>();
        for (ItemVenda item : this.itens) {
            item.setVenda(this);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
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