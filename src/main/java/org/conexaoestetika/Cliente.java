package org.conexaoestetika;

public class Cliente extends Pessoa {
    private String cpf;
    private double dinheiroGasto;

    public Cliente(int id, String nome, String telefone, String email, String cpf) {
        super(id, nome, telefone, email);
        this.setCpf(cpf);
        this.dinheiroGasto = 0.0;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    public double getDinheiroGasto() {
        return dinheiroGasto;
    }

    public void adicionarGasto(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException("Valor inválido.");
        }

        this.dinheiroGasto += valor;
    }
}
