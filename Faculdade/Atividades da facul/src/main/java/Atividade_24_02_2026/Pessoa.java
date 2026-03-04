package Atividade_24_02_2026;

public class Pessoa implements IPessoa{

    private String nome;
    private String status;

    @Override
    public String salvar (Pessoa pessoa){
        if (pessoa == null){
            throw new RuntimeException();
        }
        if(pessoa.nome == null){
            throw new ArithmeticException();
        }
        return pessoa.toString();
    }
    @Override
    public String alterarStatus(String status){
        if(!status.equalsIgnoreCase(this.status)){
            this.status = Status.ATIVO.toString();

        } else {
            this.status = Status.INATIVO.toString();

        }
        return "Atualizar com sucesso para" + this.status;

    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

