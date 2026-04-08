package service;

import model.ContaReceber;
import repository.ContaReceberRepository;

import java.util.List;

public class ContaReceberService {

    private ContaReceberRepository repository = new ContaReceberRepository();

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

        repository.salvar(conta);
    }

    public void atualizar(ContaReceber conta) {
        repository.atualizar(conta);
    }

    public List<ContaReceber> listar() {
        return repository.listarTodos();
    }

    public ContaReceber buscar(Long id) {
        return repository.buscarPorId(id);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
