

public class Pix extends Pagamento{

    private String chavePix;

    public Pix(double valor, String chavePix) {
        super(valor);
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento(){
        System.out.println("-------------------------------------");
        System.out.println("|Pix com a chave pix:" + " " + this.chavePix);
    }
}
