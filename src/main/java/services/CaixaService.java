package services;

import models.Caixa;
import models.Usuario;
import models.enums.StatusCaixa;
import models.enums.TipoUsuario;
import repositories.CaixaRepository;

import java.time.LocalDate;
import java.util.List;

public class CaixaService {

    private final CaixaRepository repository;

    public CaixaService(CaixaRepository repository) {
        this.repository = repository;
    }

    public void abrirCaixa(Caixa caixa, Usuario usuario) {
        validarPermissaoCaixa(usuario);

        Caixa existente = repository.buscarCaixaAberto();

        if (existente != null) {
            throw new IllegalArgumentException("Já existe um caixa aberto!");
        }

        if (caixa == null) {
            throw new IllegalArgumentException("Caixa inválido!");
        }

        if (caixa.getValorAbertura() < 0) {
            throw new IllegalArgumentException("Valor de abertura inválido!");
        }

        caixa.setDataAbertura(LocalDate.now());
        caixa.setDataFechamento(null);
        caixa.setStatus(StatusCaixa.ABERTO);
        caixa.setSaldoAtual(caixa.getValorAbertura());

        repository.salvar(caixa);
    }

    public void fecharCaixa(Usuario usuario) {
        validarPermissaoCaixa(usuario);

        Caixa caixa = repository.buscarCaixaAberto();

        if (caixa == null) {
            throw new IllegalArgumentException("Não existe caixa aberto!");
        }

        caixa.setDataFechamento(LocalDate.now());
        caixa.setStatus(StatusCaixa.FECHADO);

        repository.atualizar(caixa);
    }

    public Caixa buscar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        return repository.buscarPorId(id);
    }

    public List<Caixa> listar() {
        return repository.listarTodos();
    }

    public void atualizar(Caixa caixa) {
        if (caixa == null) {
            throw new IllegalArgumentException("Caixa inválido!");
        }
        repository.atualizar(caixa);
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
        repository.deletar(id);
    }

    public Caixa buscarCaixaAberto() {
        return repository.buscarCaixaAberto();
    }

    // 🔥 REGRA DE NEGÓCIO (IMPORTANTE)
    private void validarPermissaoCaixa(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário responsável pelo caixa é obrigatório.");
        }

        if (usuario.getPerfil() == null) {
            throw new IllegalArgumentException("Usuário precisa ter perfil definido.");
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException("Usuário inativo não pode operar o caixa.");
        }

        if (usuario.getPerfil() != TipoUsuario.ADMINISTRADOR &&
            usuario.getPerfil() != TipoUsuario.GERENTE) {
            throw new IllegalArgumentException("Apenas administrador ou gerente podem operar o caixa.");
        }
    }
}