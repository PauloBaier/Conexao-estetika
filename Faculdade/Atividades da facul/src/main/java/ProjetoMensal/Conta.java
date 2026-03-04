package ProjetoMensal;

import java.time.LocalDate;

public abstract class Conta {

    protected static int contadorId= 1;

    protected int id;
    protected String descricao;
    protected double valor;
    protected LocalDate dataVencimento;
    protected boolean paga;

    public Conta(String descricao, double valor, LocalDate dataVencimento){

        if (valor <= 0 ){
            throw new IllegalStateException("Valor deve ser positivo");
        }
        this.id = contadorId++;
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.paga = false;

    }

    public void marcarComopaga(){
        if (paga){
            throw new IllegalStateException("Conta já está paga.");
        }
        this.paga = true;
    }
    public boolean isPaga(){
        return paga;
    }

}
