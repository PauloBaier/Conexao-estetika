public class Vaga {

    private int numero;
    private String placa;
    private boolean ocupada;

    public Vaga(int numero) {
        this.numero = numero;
        this.placa = "";
        this.ocupada = false;
    }


    public int getNumero() {
        return numero;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        if (ocupada) {
            System.out.println("=======================");
            return "Vaga| " + numero + " - Ocupada - Placa: " + placa;
        } else {
            System.out.println("========================");
            return "Vaga| " + numero + " - Livre";
        }
    }
}