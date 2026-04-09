package service;

import model.MovimentacaoCaixa;
import model.enums.TipoMovimento;
import repository.MovimentacaoCaixaRepository;
import model.enums.StatusCaixa;
import model.Caixa;

import java.time.LocalDateTime;
import java.util.List;

public class MovimentacaoCaixaService {

    private MovimentacaoCaixaRepository repository;

    public MovimentacaoCaixaService(MovimentacaoCaixaRepository repository) {
        this.repository = repository;
    }

    public void registrarMovimentacao(MovimentacaoCaixa mov){

        if (mov.getCaixa() == null) {
            throw new IllegalArgumentException("Movimentação precisa de um caixa!");
        }

        if (mov.getTipo() == null){
            throw new IllegalArgumentException("Tipo de movimentação obrigatória!");
        }

        if (mov.getValor() <= 0){
            throw new IllegalArgumentException("Valor Inválido!");
        }

        if (mov.getDataMovimentacao() == null){
            mov.setDataMovimentacao(LocalDateTime.now());
        }

        if (mov.getDataMovimentacao().isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Data da movimentação não pode ser no futuro!");
        }

        if (mov.getCaixa().getStatus() != StatusCaixa.ABERTO) {
            throw new IllegalArgumentException("Não é possivel movimentar um caixa fechado!");
        }

        repository.salvar(mov);
    }

    public List<MovimentacaoCaixa> listar() {
        return repository.listarTodos();
    }

    public double calcularSaldo(Caixa caixa){

        List<MovimentacaoCaixa> lista = repository.listarTodos();
        double saldo = caixa.getValorAbertura();

        for (MovimentacaoCaixa mov : lista) {
            if (mov.getTipo() == TipoMovimento.ENTRADA) {
                saldo += mov.getValor();
            } else {
                saldo -= mov.getValor();
            }
        }

        return saldo;
    }

    public void atualizarMovimentacao(MovimentacaoCaixa mov){
        if (mov.getCaixa() == null) {
            throw new IllegalArgumentException("Movimentação precisa de um caixa!");
        }

        if (mov.getTipo() == null){
            throw new IllegalArgumentException("Tipo de movimentação obrigatória!");
        }

        if (mov.getValor() <= 0){
            throw new IllegalArgumentException("Valor Inválido!");
        }

        if (mov.getDataMovimentacao() == null){
            mov.setDataMovimentacao(LocalDateTime.now());
        }

        if (mov.getDataMovimentacao().isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Data da movimentação não pode ser no futuro!");
        }

        if (mov.getCaixa().getStatus() != StatusCaixa.ABERTO) {
            throw new IllegalArgumentException("Não é possivel movimentar um caixa fechado!");
        }

        repository.atualizar(mov);
    }

    public MovimentacaoCaixa buscar(Long id) {
        return repository.buscarPorId(id);
    }

    public MovimentacaoCaixa deletar(Long id){
        MovimentacaoCaixa mov = repository.buscarPorId(id);
        repository.deletar(id);
        return mov;
    }
}
