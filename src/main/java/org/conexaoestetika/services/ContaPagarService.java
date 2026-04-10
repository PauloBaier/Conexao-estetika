package org.conexaoestetika.services;

import model.ContaPagar;
import repository.ContaPagarRepository;

import java.util.List;

public class ContaPagarService {

    private ContasPagarRepository contasPagarRepository;

    public ContaPagarService(ContasPagarRepository contasPagarRepository) {
        this.contasPagarRepository = contasPagarRepository;
    }

    public void cadastrar(ContaPagar conta) {

        if (conta.getFornecedor() == null) {
            throw new IllegalArgumentException("Fornecedor obrigatório.");
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

        if (conta.getDataPagamento() != null &&
                conta.getDataPagamento().isBefore(conta.getDataEmissao())) {
            throw new IllegalArgumentException("Data de pagamento inválida.");
        }

        contasPagarRepository.salvar(conta);
    }

    public void atualizar(ContaPagar conta) {
        contasPagarRepository.atualizar(conta);
    }

    public List<ContaPagar> listar() {
        return contasPagarRepository.listarTodos();
    }

    public ContaPagar buscar(Long id) {
        return contasPagarRepository.buscarPorId(id);
    }

    public void deletar(Long id) {
        contasPagarRepository.deletar(id);
    }
}
