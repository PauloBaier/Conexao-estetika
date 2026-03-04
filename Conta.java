package ProjetoMensal_1;

import java.time.LocalDate;

public abstract class Conta {

    private String id;
    private double valor;
    private LocalDate data;
    private boolean pago;

    public Conta(String id, double valor, LocalDate data) {

        setId(id);
        setValor(valor);
        setData(data);
        this.pago = false;
    }


    public String getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isPago() {
        return pago;
    }

    public boolean estaPago() {
        return this.pago;
    }


    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID não pode ser vazio.");
        }
        this.id = id;
    }

    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula.");
        }
        this.data = data;
    }

    public void setPago(boolean pago) {
        if (this.pago && pago) {
            throw new IllegalStateException("Conta já está paga.");
        }
        this.pago = pago;
    }
}