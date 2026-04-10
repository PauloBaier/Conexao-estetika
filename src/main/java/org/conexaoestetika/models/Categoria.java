package org.conexaoestetika.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity                         // Diz ao JPA: que seria uma tabela
@Table(name = "categorias")     // como ficaria a tabela no Banco

public class Categoria {


    @Id         //campo que seria a chave primaria
    @GeneratedValue( strategy = GenerationType.IDENTITY )     //- Banco gerando id automaticamente
    private Long id;

    @Column(nullable = false, length = 100)      //-          // NOT NULL, Capacidade até 100 carct
    private String nome;

    @Column(length = 255)                        //-           //Seria opcional e Capacidade até 255 carct
    private String descricao;

    @Column(nullable = false)
    private boolean ativo = true;                //-           // já nasce ativa por padrão

    @Column(nullable = false)
    private LocalDate dataCadastro = LocalDate.now();      // já preenche sozinho


    public Categoria() {}                                   // Serve para funcionar o JPA

    public Categoria(String nome, String descricao) {
        this.nome=nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;               //
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;     //
    }

    public boolean isAtivo() {
        return ativo;                   //
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
}

