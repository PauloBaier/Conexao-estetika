package org.conexaoestetika.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fornecedor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "razao_social")
    private String razaoSocial;

    @ManyToMany(mappedBy = "fornecedores")
    private List<Produto> produtos;

    public Fornecedor(){};

    public Fornecedor(String nome, String telefone, String email, String cnpj, String razaoSocial, List<Produto> produtos) {
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setCnpj(cnpj);
        this.setRazaoSocial(razaoSocial);
        this.setProdutos(produtos);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(telefone == null || telefone.length() < 8) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if(cnpj == null ||cnpj.length() != 14){
            throw new IllegalArgumentException("ERRO: Cnpj Inválido");
        }
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        if(razaoSocial == null || razaoSocial.trim().isEmpty()){
            throw new IllegalArgumentException("ERRO: Razao Social Inválida!");
        }
        this.razaoSocial = razaoSocial;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        if(produtos == null){
            throw new IllegalArgumentException("ERRO: Produtos não poder ser nulo!");
        }
        this.produtos = produtos;
    }
}
