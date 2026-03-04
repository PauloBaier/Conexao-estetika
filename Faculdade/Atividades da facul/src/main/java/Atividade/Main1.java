
public class Main1 {
    public static void main(String[] args) {
        Pix pagamento1 = new Pix(100, "020.435.123.90");
        CartaoDeCredito pagamento2 = new CartaoDeCredito(100, 2);
        pagamento1.processarPagamento();
        pagamento2.processarPagamento();
    }

}
