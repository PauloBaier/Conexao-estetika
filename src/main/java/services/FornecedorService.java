package services;

import models.Fornecedor;
import repositories.FornecedorRepository;

import java.util.List;

public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public void salvar(Fornecedor fornecedor) {
        validarFornecedor(fornecedor);
        fornecedorRepository.salvar(fornecedor);
    }

    public void atualizar(Fornecedor fornecedor) {
        if (fornecedor == null || fornecedor.getId() == null) {
            throw new IllegalArgumentException("Fornecedor inválido.");
        }

        Fornecedor existente = fornecedorRepository.buscarPorId(fornecedor.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Fornecedor não encontrado.");
        }

        validarFornecedor(fornecedor);
        fornecedorRepository.atualizar(fornecedor);
    }

    public void delete(Fornecedor fornecedor) {
        if (fornecedor == null || fornecedor.getId() == null) {
            throw new IllegalArgumentException("Fornecedor inválido.");
        }

        if (fornecedorRepository.buscarPorId(fornecedor.getId()) == null) {
            throw new IllegalArgumentException("Fornecedor não encontrado.");
        }

        fornecedorRepository.delete(fornecedor);
    }

    public Fornecedor buscarPorId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que 0.");
        }

        return fornecedorRepository.buscarPorId(id);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.listarTodos();
    }

    private void validarFornecedor(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
        }

        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome é obrigatório.");
        }

        if (fornecedor.getTelefone() == null || fornecedor.getTelefone().trim().length() < 8) {
            throw new RuntimeException("O telefone é obrigatório.");
        }

        if (fornecedor.getEmail() == null || fornecedor.getEmail().trim().isEmpty() || !fornecedor.getEmail().contains("@")) {
            throw new RuntimeException("O email é obrigatório e deve ser válido.");
        }

        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().length() != 14) {
            throw new RuntimeException("O CNPJ é obrigatório e deve ter 14 números.");
        }

        if (fornecedor.getRazaoSocial() == null || fornecedor.getRazaoSocial().trim().isEmpty()) {
            throw new RuntimeException("A razão social é obrigatória.");
        }
    }
}