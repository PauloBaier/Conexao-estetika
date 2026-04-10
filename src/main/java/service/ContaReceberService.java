package service;

import model.ContaReceber;
import repository.ContaReceberRepository;

import java.util.List;
import jakarta.persistence.*;

public class ContaReceberService {

    private ContaReceberRepository contaReceberRepository;

    public ContaReceberService(ContaReceberRepository contaReceberRepository) {
        this.contaReceberRepository = contaReceberRepository;
    }

    public void cadastrar(ContaReceber conta) {

        if (conta.getCliente() == null) {
            throw new IllegalArgumentException("Cliente obrigatório.");
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

        contaReceberRepository.salvar(conta);
    }

    public void atualizar(ContaReceber conta) {
        contaReceberRepository.atualizar(conta);
    }

    public List<ContaReceber> listar() {
        return contaReceberRepository.listarTodos();
    }

    public ContaReceber buscar(Long id) {
        return contaReceberRepository.buscarPorId(id);
    }

    public void deletar(Long id) {
        contaReceberRepository.deletar(id);
    }
}