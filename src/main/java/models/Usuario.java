package models;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome", nullable = false)
    private String nome;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column (name = "perfil", nullable = false)
    private String perfil;

    @Column (name = "ativo", nullable = false)
    private boolean ativo;

    @Column (name = "criado_em", nullable = false)
    private LocalDate criado_em;


    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, String perfil, boolean ativo, LocalDate criado_em) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ativo = ativo;
        this.criado_em = criado_em;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(LocalDate criado_em) {
        this.criado_em = criado_em;
    }

}
