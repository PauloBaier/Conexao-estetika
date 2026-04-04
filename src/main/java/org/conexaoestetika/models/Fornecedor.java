package org.conexaoestetika.models;

public class Fornecedor extends Pessoa {
    private String cnpj;
    private String razaoSocial;

    public Fornecedor(int id, String nome, String telefone, String email, String cnpj, String razaoSocial) {
        super(id, nome, telefone,email);
        this.setCnpj(cnpj);
        this.setRazaoSocial(razaoSocial);
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setCnpj(String cnpj) {
        if(cnpj == null || cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
        this.cnpj = cnpj;
    }

    public void setRazaoSocial(String razaoSocial) {
        if(razaoSocial == null || razaoSocial.trim().isEmpty()) {
            throw new IllegalArgumentException("Razão social não pode ser vazia.");
        }
        this.razaoSocial = razaoSocial;
    }
}
