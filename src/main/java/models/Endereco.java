package models;

import jakarta.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua", nullable = false, length = 100)
    private String rua;

    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "fk_clientes_id", nullable = false)
    private Cliente cliente;

    public Endereco() {
    }

    public Endereco(Long id, String rua, String bairro, String numero, String cep, Cliente cliente) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}