package services;

import models.Caixa;
import models.MovimentacaoCaixa;
import models.enums.StatusCaixa;
import models.enums.TipoMovimento;
import repositories.CaixaRepository;
import repositories.MovimentacaoCaixaRepository;
import models.Usuario;
import models.enums.TipoUsuario;
import java.time.LocalDateTime;
import java.util.List;

public class MovimentacaoCaixaService {

    private final MovimentacaoCaixaRepository repository;
    private final CaixaRepository caixaRepository;

    public MovimentacaoCaixaService(MovimentacaoCaixaRepository repository, CaixaRepository caixaRepository) {
        this.repository = repository;
        this.caixaRepository = caixaRepository;
    }

    public void registrarMovimentacao(MovimentacaoCaixa mov, Usuario usuario) {
        validarPermissaoUsuario(usuario);
        validarMovimentacao(mov);

        Caixa caixa = caixaRepository.buscarPorId(mov.getCaixa().getId());

        if (caixa == null) {
            throw new IllegalArgumentException("Caixa não encontrado!");
        }

        if (caixa.getStatus() != StatusCaixa.ABERTO) {
            throw new IllegalArgumentException("Não é possível movimentar um caixa fechado!");
        }

        if (mov.getTipo() == TipoMovimento.SAIDA && caixa.getSaldoAtual() < mov.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente no caixa!");
        }

        mov.setCaixa(caixa);

        if (mov.getDataMovimentacao() == null) {
            mov.setDataMovimentacao(LocalDateTime.now());
        }

        if (mov.getTipo() == TipoMovimento.ENTRADA) {
            caixa.setSaldoAtual(caixa.getSaldoAtual() + mov.getValor());
        } else {
            caixa.setSaldoAtual(caixa.getSaldoAtual() - mov.getValor());
        }

        repository.salvar(mov);
        caixaRepository.atualizar(caixa);
    }

    public List<MovimentacaoCaixa> listar() {
        return repository.listarTodos();
    }

    public double calcularSaldo(Caixa caixa) {
        if (caixa == null) {
            throw new IllegalArgumentException("Caixa inválido!");
        }

        Caixa caixaBanco = caixaRepository.buscarPorId(caixa.getId());

        if (caixaBanco == null) {
            throw new IllegalArgumentException("Caixa não encontrado!");
        }

        return caixaBanco.getSaldoAtual();
    }

    public void atualizarMovimentacao(MovimentacaoCaixa mov) {
        if (mov == null || mov.getId() == null) {
            throw new IllegalArgumentException("Movimentação inválida!");
        }

        MovimentacaoCaixa existente = repository.buscarPorId(mov.getId());

        if (existente == null) {
            throw new IllegalArgumentException("Movimentação não encontrada!");
        }

        throw new UnsupportedOperationException(
                "Atualizar movimentação já registrada não é permitido, pois afeta o saldo do caixa. Exclua e lance novamente."
        );
    }

    public MovimentacaoCaixa deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }

        MovimentacaoCaixa mov = repository.buscarPorId(id);

        if (mov == null) {
            throw new IllegalArgumentException("Movimentação não encontrada!");
        }

        Caixa caixa = caixaRepository.buscarPorId(mov.getCaixa().getId());

        if (caixa == null) {
            throw new IllegalArgumentException("Caixa não encontrado!");
        }

        if (caixa.getStatus() == StatusCaixa.FECHADO) {
            throw new IllegalArgumentException("Não pode alterar movimentação de caixa fechado!");
        }

        if (mov.getTipo() == TipoMovimento.ENTRADA) {
            if (caixa.getSaldoAtual() < mov.getValor()) {
                throw new IllegalArgumentException("Não é possível remover essa entrada porque o saldo ficaria negativo.");
            }
            caixa.setSaldoAtual(caixa.getSaldoAtual() - mov.getValor());
        } else {
            caixa.setSaldoAtual(caixa.getSaldoAtual() + mov.getValor());
        }

        repository.deletar(id);
        caixaRepository.atualizar(caixa);

        return mov;
    }

    private void validarMovimentacao(MovimentacaoCaixa mov) {
        if (mov == null) {
            throw new IllegalArgumentException("Movimentação inválida!");
        }

        if (mov.getCaixa() == null || mov.getCaixa().getId() == null) {
            throw new IllegalArgumentException("Movimentação precisa de um caixa válido!");
        }

        if (mov.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de movimentação obrigatório!");
        }

        if (mov.getValor() <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }

        if (mov.getDescricao() == null || mov.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição obrigatória!");
        }

        if (mov.getDataMovimentacao() != null && mov.getDataMovimentacao().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data da movimentação não pode ser no futuro!");
        }
    }
    private void validarPermissaoUsuario(Usuario usuario) {
    if (usuario == null) {
        throw new IllegalArgumentException("Usuário é obrigatório para movimentar o caixa.");
    }

    if (usuario.getPerfil() == null) {
        throw new IllegalArgumentException("Usuário precisa ter perfil definido.");
    }

    if (!usuario.isAtivo()) {
        throw new IllegalArgumentException("Usuário inativo não pode movimentar o caixa.");
    }

    if (usuario.getPerfil() != TipoUsuario.ADMINISTRADOR &&
        usuario.getPerfil() != TipoUsuario.GERENTE) {
        throw new IllegalArgumentException("Apenas administrador ou gerente podem movimentar o caixa.");
    }
}
}