package services;

import models.Cliente;
import models.Endereco;
import repositories.ClienteRepository;
import repositories.EnderecoRepository;

import java.util.List;

public class EnderecoService {

    private final EnderecoRepository enderecoRepo;
    private final ClienteRepository clienteRepo;

    public EnderecoService(EnderecoRepository enderecoRepo, ClienteRepository clienteRepo) {
        this.enderecoRepo = enderecoRepo;
        this.clienteRepo = clienteRepo;
    }

    public void cadastrarEndereco(Endereco endereco, Long clienteId) {
        if (endereco == null) {
            throw new RuntimeException("Endereço não pode ser nulo");
        }

        if (clienteId == null || clienteId <= 0) {
            throw new RuntimeException("ID do cliente inválido");
        }

        Cliente cliente = clienteRepo.buscarPorId(clienteId);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        validarEndereco(endereco);

        endereco.setCliente(cliente);
        enderecoRepo.salvar(endereco);
    }

    public List<Endereco> listar() {
        return enderecoRepo.listar();
    }

    public Cliente buscarClientePorId(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        Cliente cliente = clienteRepo.buscarPorId(id);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return cliente;
    }

    private void validarEndereco(Endereco endereco) {
        if (endereco.getRua() == null || endereco.getRua().trim().isEmpty()) {
            throw new RuntimeException("Rua obrigatória");
        }

        if (endereco.getBairro() == null || endereco.getBairro().trim().isEmpty()) {
            throw new RuntimeException("Bairro obrigatório");
        }

        if (endereco.getNumero() == null || endereco.getNumero().trim().isEmpty()) {
            throw new RuntimeException("Número obrigatório");
        }

        if (endereco.getCep() == null || endereco.getCep().trim().length() != 8) {
            throw new RuntimeException("CEP inválido");
        }
    }
}