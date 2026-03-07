package org.conexaoestetika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Financeiro {
    private List<ContaReceber> contasReceber;
    private List<ContaPagar> contasPagar;

    private int ultimoIdContaReceber = 0;
    private int ultimoIdContaPagar = 0;


    //construtor
    public Financeiro() {
        this.contasReceber = new ArrayList<>();
        this.contasPagar = new ArrayList<>();
    }

    //getter
    public List<ContaReceber> getContasReceber() {
        return Collections.unmodifiableList(contasReceber);
    }

    public List<ContaPagar> getContasPagar() {
        return Collections.unmodifiableList(contasPagar);
    }

    //METODOS
    //contas a receber

    public void adicionarContaReceber(ContaReceber conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula.");
        }

        contasReceber.add(conta);
        this.ultimoIdContaReceber++;
    }

    public boolean alterarStatusContaReceber(int id, boolean novoStatus) {
        for (ContaReceber conta : contasReceber) {
            if (conta.getId() == id) {
                conta.setPago(novoStatus);
                return true;
            }
        }
        return false;
    }

    public boolean removerContaReceber(int id) {
        return contasReceber.removeIf(conta -> conta.getId() == id);
    }

    public List<ContaReceber> listarTodasContasReceber() {
        return new ArrayList<>(contasReceber);
    }

    //contas a pagar
    public void adicionarContaPagar(ContaPagar conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula.");
        }

        contasPagar.add(conta);
        this.ultimoIdContaPagar++;
    }

    public boolean alterarStatusContaPagar(int id, boolean novoStatus) {
        for (ContaPagar conta : contasPagar) {
            if (conta.getId() == id) {
                conta.setPago(novoStatus);
                return true;
            }
        }
        return false;
    }

    public boolean removerContaPagar(int id) {
        return contasPagar.removeIf(conta -> conta.getId() == id);
    }

    public List<ContaPagar> listarTodasContasPagar() {
        return new ArrayList<>(contasPagar);
    }

    public int getUltimoIdContaReceber() {
        return ultimoIdContaReceber;
    }

    public int getUltimoIdContaPagar() {
        return ultimoIdContaPagar;
    }
}