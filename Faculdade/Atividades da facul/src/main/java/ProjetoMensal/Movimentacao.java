package ProjetoMensal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movimentacao {

    private static int contadorId = 1;

    private int id;
    private String tipo; //ENTRADA OU SAÍDA
    private double valor;
    private LocalDate data;

    public Movimentacao(String tipo, double valor){

        if (!tipo.equals("ENTRADA") && !tipo.equals("SAÍDA")) {
            throw new IllegalStateException("Tipo deve ser ENTRADA ou SAIDA.");

            this.id = contadorId++;
            this.tipo = tipo;
            this.valor = valor;
            this.data = LocalDateTime.now();
        }
    }
    public String getTipo(){
        return tipo;
    }
    public double getValor(){
        return valor;
    }


}
