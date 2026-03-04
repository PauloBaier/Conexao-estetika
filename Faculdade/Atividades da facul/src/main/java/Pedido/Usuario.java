package Pedido;

public class Usuario {
    private String nome;
    private  String senha;
    private  String endereco;
    private String email;

    public Usuario(String nome, String senha, String endereco, String email) {
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }
}
