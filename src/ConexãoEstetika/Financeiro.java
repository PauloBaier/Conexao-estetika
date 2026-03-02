package ConexãoEstetika;

public class Financeiro {
    private List<ContaReceber> contasReceber;
    private List<ContaPagar> contasPagar;

    private List<Movimentacao> movimentacoes; //registra (entrata/saida), valor, data, descrição, referencia (venda, conta, etc.)

    private double saldoAtual;
    private double totalEntradas;
    private double totalSaidas;

    //construtor
    public Financeiro() {
        this.contasReceber = new ArrayList<>();
        this.contasPagar = new ArrayList<>();
        this.movimentacoes = new ArrayList<>();
        this.saldoAtual = 0.0;
        this.totalEntradas = 0.0;
        this.totalSaidas = 0.0;
    }

    //getters
    public List<ContaReceber> getContasReceber() {
        return Collections.unmodifiableList(contasReceber);
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public double getTotalEntradas() {
        return totalEntradas;
    }

    public double getTotalSaidas() {
        return totalSaidas;
    }


    //METODOS
    //contas a receber
    public void adicionarContaReceber(ContaReceber conta)
    public void receberConta(ContaReceber conta)  //marcar conta como paga e somar no saldo

    //contas a pagar
    public void adicionarContaPagar(ContaPagar conta)
    public void pagarConta(ContaPagar conta) //marcar conta como paga e subtrair do saldo

    //atualizar saldo
    private void atualizarSaldo(double valor)

    //consultas
    public double getSaldoAtual()
    public double calcularTotalEntradas()
    public double calcularTotalSaidas()

    //movimentacoes
    private void registrarMovimentacao(String tipo, double valor, String descricao)

    public List<ContaReceber> listarContasReceberEmAberto()
    public List<ContaPagar> listarContasPagarEmAberto()
}
