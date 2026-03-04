
public class CartaoDeCredito extends Pagamento{

    private int parcelas;

    public CartaoDeCredito(double valor, int parcelas) {
        super(valor);
        this.parcelas = parcelas;
    }


    @Override
    public void processarPagamento(){
        System.out.println("=====================================");
        System.out.println("========= V a l o r e s =============");
        System.out.println("-------------------------------------");
        System.out.println("|Valor do pagamento " + this.getValor() + "\n|Quantidade " + "de pagamento: " + this.parcelas);
        System.out.println("--------------------------------------");
    }

}
