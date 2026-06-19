package services;

import models.ContaReceber;
import repositories.ContaReceberRepository;

import java.util.List;

public class ContaReceberService {

    private final ContaReceberRepository contaReceberRepository;

    public ContaReceberService(ContaReceberRepository contaReceberRepository) {
        this.contaReceberRepository = contaReceberRepository;
    }

    public void cadastrar(ContaReceber conta) {
        validarContaReceber(conta);
        contaReceberRepository.salvar(conta);
    }

    public void atualizar(ContaReceber conta) {
        if (conta == null || conta.getId() == null) {
            throw new IllegalArgumentException("Conta inválida.");
        }

        ContaReceber existente = contaReceberRepository.buscarPorId(conta.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        validarContaReceber(conta);
        contaReceberRepository.atualizar(conta);
    }

    public List<ContaReceber> listar() {
        return contaReceberRepository.listarTodos();
    }

    public ContaReceber buscar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return contaReceberRepository.buscarPorId(id);
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        ContaReceber existente = contaReceberRepository.buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

        contaReceberRepository.deletar(id);
    }

    private void validarContaReceber(ContaReceber conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta inválida.");
        }

        if (conta.getVenda() == null) {
            throw new IllegalArgumentException("Venda obrigatória.");
        }

        if (conta.getDescricao() == null || conta.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição obrigatória.");
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