package ProjetoMensal1;

public class Fornecedor extends Pessoa{
    private String cnpj;
    private String razaoSozial;

    public Fornecedor(int id, String nome, String telefone, String email, String cnpj, String razaoSozial) {
        super(id, nome, telefone, razaoSozial);
        this.setCnpj(cnpj);
        this.setRazaoSozial(razaoSozial);
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSozial() {
        return razaoSozial;
    }

    public void setCnpj(String cnpj) {
        if(cnpj == null || cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
        this.cnpj = cnpj;
    }

    public void setRazaoSozial(String razaoSozial) {
        if(razaoSozial == null || razaoSozial.trim().isEmpty()) {
            throw new IllegalArgumentException("Razão social não pode ser vazia.");
        }
        this.razaoSozial = razaoSozial;
    }
}
