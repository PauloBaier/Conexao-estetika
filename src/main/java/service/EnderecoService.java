package service;

import model.Cliente;
import model.Endereco;
import repository.ClienteRepository;
import repository.EnderecoRepository;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class EnderecoService {

    private EnderecoRepository enderecoRepo;
    private ClienteRepository clienteRepo;

    public EnderecoService(EnderecoRepository enderecoRepo, ClienteRepository clienteRepo) {
        this.enderecoRepo = enderecoRepo;
        this.clienteRepo = clienteRepo;
    }

    public EnderecoService() {

    }

    public void cadastrarEndereco(Endereco endereco, Long clienteId) {

        if (endereco == null) {
            throw new RuntimeException("Endereço não pode ser nulo");
        }

        Cliente cliente = clienteRepo.buscarPorId(clienteId);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        endereco.setCliente(cliente);
        enderecoRepo.salvar(endereco);
    }

    public void validarLimiteEnderecos(Cliente cliente) {

        if (cliente.getEnderecos() == null) {
            return;
        }

        if (cliente.getEnderecos().size() >= 3) {
            throw new RuntimeException("Limite de 3 endereços atingido");
        }
    }

    public void validarCep(String cep) {
        if (cep == null || cep.length() != 8) {
            throw new RuntimeException("CEP inválido (deve ter 8 números)");
        }
    }

    public List<Endereco> listar() {
        return enderecoRepo.listar();
    }

    public Cliente buscarClientePorId(Long id) {

        if (id == null) {
            throw new RuntimeException("O ID não pode ser nulo");
        }

        Cliente cliente = clienteRepo.buscarPorId(id);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return cliente;
    }
}