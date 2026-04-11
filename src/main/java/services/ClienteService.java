package services;

import models.Cliente;
import models.Endereco;
import repositories.ClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Cliente cliente) {
        validarDadosCliente(cliente);
        repository.salvar(cliente);
    }

    public void atualizar(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new RuntimeException("Cliente inválido.");
        }

        validarDadosCliente(cliente);
        repository.atualizar(cliente);
    }

    public void atualizarEmail(Long clienteId, String novoEmail) {
        if (novoEmail == null || novoEmail.trim().isEmpty() || !novoEmail.contains("@")) {
            throw new RuntimeException("O email é obrigatório e deve ser válido.");
        }

        Cliente cliente = repository.buscarPorId(clienteId);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        cliente.setEmail(novoEmail.trim());
        repository.atualizar(cliente);
    }

    public void adicionarEnderecoAoCliente(Long clienteId, Endereco endereco) {
        Cliente cliente = repository.buscarPorId(clienteId);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        if (endereco == null) {
            throw new RuntimeException("Endereço inválido.");
        }

        if (cliente.getEnderecos() != null && cliente.getEnderecos().size() >= 3) {
            throw new RuntimeException("Cliente já possui o limite de 3 endereços.");
        }

        endereco.setCliente(cliente);
        cliente.getEnderecos().add(endereco);

        repository.atualizar(cliente);
    }

    public Cliente buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido.");
        }

        return repository.buscarPorId(id);
    }

    public List<Cliente> listar() {
        return repository.listar();
    }

    public List<Cliente> listarTodos() {
        return repository.listar();
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido.");
        }

        Cliente cliente = repository.buscarPorId(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        repository.deletar(id);
    }

    private void validarDadosCliente(Cliente cliente) {
        if (cliente == null) {
            throw new RuntimeException("Cliente inválido.");
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome é obrigatório.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().trim().length() < 8) {
            throw new RuntimeException("O telefone é obrigatório.");
        }

        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty() || !cliente.getEmail().contains("@")) {
            throw new RuntimeException("O email é obrigatório e deve ser válido.");
        }

        if (cliente.getCpf() == null || cliente.getCpf().trim().length() != 11) {
            throw new RuntimeException("O CPF é obrigatório e deve ter 11 números.");
        }
    }
}