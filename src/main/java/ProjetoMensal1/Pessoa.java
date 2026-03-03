package ProjetoMensal1;

public abstract class Pessoa {
    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Pessoa(int id, String nome, String telefone, String email) {
        this.id = id;
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
    }

    public double getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        this.id = id;
    }

    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        if(telefone == null || telefone.length() < 8) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        if(telefone == null || !email.contains("@"))
            throw new IllegalArgumentException("Email inválido.");
        this.email = email;
    }
}
