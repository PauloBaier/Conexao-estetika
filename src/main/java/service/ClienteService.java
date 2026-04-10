package service;

import model.Cliente;
import model.Endereco;
import repository.ClienteRepository;
import java.util.List;
import jakarta.persistence.*;

public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    private void validarDadosCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RuntimeException("O Nome é obrigatório");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().length() < 8) {
            throw new RuntimeException("O Telefone é obrigatório");
        }

        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            throw new RuntimeException("O Email é obrigatório e deve ser válido");
        }

        if (cliente.getCpf() == null || cliente.getCpf().length() != 11) {
            throw new RuntimeException("O CPF é obrigatório");
        }
    }

    public void cadastrar(Cliente cliente) {
        validarDadosCliente(cliente);
        repository.salvar(cliente);
    }

    public void atualizar(Cliente cliente) {
        validarDadosCliente(cliente);
        repository.atualizar(cliente);
    }

    public void atualizarEmail(Long clienteId, String novoEmail) {
        if (novoEmail == null || !novoEmail.contains("@")) {
            throw new RuntimeException("O Email é obrigatório e deve ser válido");
        }

        Cliente cliente = repository.buscarPorId(clienteId);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        cliente.setEmail(novoEmail);
        repository.atualizar(cliente);
    }

    public void adicionarEnderecoAoCliente(Long clienteId, Endereco endereco) {
        Cliente cliente = repository.buscarPorId(clienteId);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        if (cliente.getEnderecos() != null && cliente.getEnderecos().size() >= 3) {
            throw new RuntimeException("Cliente já possui o limite de 3 endereços");
        }

        endereco.setCliente(cliente);
        cliente.getEnderecos().add(endereco);
        repository.atualizar(cliente);
    }

    public List<Cliente> listar() {
        return repository.listar();
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }


    public List<Cliente> listarTodos() {
        return repository.listar();
    }

}