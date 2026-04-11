package services;

import models.ContaPagar;
import repositories.ContaPagarRepository;

import java.util.List;

public class ContaPagarService {

    private final ContaPagarRepository contaPagarRepository;

    public ContaPagarService(ContaPagarRepository contaPagarRepository) {
        this.contaPagarRepository = contaPagarRepository;
    }

    public void cadastrar(ContaPagar conta) {
        validarContaPagar(conta);
        contaPagarRepository.salvar(conta);
    }

    public void atualizar(ContaPagar conta) {
        if (conta == null || conta.getId() == null) {
            throw new IllegalArgumentException("Conta inválida.");
        }

        ContaPagar existente = contaPagarRepository.buscarPorId(conta.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        validarContaPagar(conta);
        contaPagarRepository.atualizar(conta);
    }

    public List<ContaPagar> listar() {
        return contaPagarRepository.listarTodos();
    }

    public ContaPagar buscar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return contaPagarRepository.buscarPorId(id);
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        ContaPagar existente = contaPagarRepository.buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        contaPagarRepository.deletar(id);
    }

    private void validarContaPagar(ContaPagar conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta inválida.");
        }

        if (conta.getFornecedor() == null) {
            throw new IllegalArgumentException("Fornecedor obrigatório.");
        }

        if (conta.getDescricao() == null || conta.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição obrigatória.");
        }

        if (conta.getTipoDespesas() == null || conta.getTipoDespesas().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de despesa obrigatório.");
        }

        if (conta.getValor() <= 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }

        if (conta.getDataEmissao() == null) {
            throw new IllegalArgumentException("Data de emissão é obrigatória.");
        }

        if (conta.getDataVencimento() == null) {
            throw new IllegalArgumentException("Data de vencimento é obrigatória.");
        }

        if (conta.getDataVencimento().isBefore(conta.getDataEmissao())) {
            throw new IllegalArgumentException("Data de vencimento não pode ser antes da emissão.");
        }

        if (conta.getDataPagamento() != null && conta.getDataPagamento().isBefore(conta.getDataEmissao())) {
            throw new IllegalArgumentException("Data de pagamento inválida.");
        }

        if (conta.getStatus() == null) {
            throw new IllegalArgumentException("Status obrigatório.");
        }
    }
}