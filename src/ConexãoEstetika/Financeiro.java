package ConexãoEstetika;

public class Financeiro {
    private List<ContaReceber> contasReceber;
    private List<ContaPagar> contasPagar;

    private List<Movimentacao> movimentacoes; //registra (entrata/saida), valor, data, descrição, referencia (venda, conta, etc.)


    //construtor
    public Financeiro() {
        this.contasReceber = new ArrayList<>();
        this.contasPagar = new ArrayList<>();
        this.movimentacoes = new ArrayList<>();

    }

    //getters
    public List<ContaReceber> getContasReceber() {
        return Collections.unmodifiableList(contasReceber);
    }



    //METODOS
    //contas a receber
    public void adicionarContaReceber(ContaReceber conta)
    public void receberConta(ContaReceber conta)  //marcar conta como paga e somar no saldo

    //contas a pagar
    public void adicionarContaPagar(ContaPagar conta)
    public void pagarConta(ContaPagar conta) //marcar conta como paga e subtrair do saldo


    //movimentacoes
    private void registrarMovimentacao(String tipo, double valor, String descricao)

    public List<ContaReceber> listarContasReceberEmAberto()
    public List<ContaPagar> listarContasPagarEmAberto()
}
