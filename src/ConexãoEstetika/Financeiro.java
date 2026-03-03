package ConexãoEstetika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Financeiro {
    private List<ContaReceber> contasReceber;
    private List<ContaPagar> contasPagar;


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
        contasReceber.add(conta);
    }

    public boolean alterarStatusContaReceber(String id, boolean novoStatus) {
        for (ContaReceber conta : contasReceber) {
            if (conta.getId().equals(id)) {
                conta.setPaga(novoStatus);
                return true;
            }
        }
        return false;
    }

    public boolean removerContaReceber(String id) {
        return contasReceber.removeIf(conta -> conta.getId().equals(id));
    }

    public List<ContaReceber> listarTodasContasReceber() {
        return new ArrayList<>(contasReceber);
    }

    //contas a pagar
    public void adicionarContaPagar(ContaPagar conta) {
        contasPagar.add(conta);
    }

    public boolean alterarStatusContaPagar(String id, boolean novoStatus) {
        for (ContaPagar conta : contasPagar) {
            if (conta.getId().equals(id)) {
                conta.setPaga(novoStatus);
                return true;
            }
        }
        return false;
    }

    public boolean removerContaPagar(String id) {
        return contasPagar.removeIf(conta -> conta.getId().equals(id));
    }

    public List<ContaPagar> listarTodasContasPagar() {
        return new ArrayList<>(contasPagar);
    }


