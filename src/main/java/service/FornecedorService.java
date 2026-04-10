package service;

import model.Fornecedor;
import repository.FornecedorRepository;
import jakarta.persistence.*;

import java.util.List;

public class FornecedorService {
    FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public void salvar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("ERRO: Fornecedor não pode ser nulo!");
        }

        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
            throw new RuntimeException("O Nome é obrigatório");
        }

        if (fornecedor.getTelefone() == null || fornecedor.getTelefone().length() < 8) {
            throw new RuntimeException("O Telefone é obrigatório");
        }

        if (fornecedor.getEmail() == null || !fornecedor.getEmail().contains("@")) {
            throw new RuntimeException("O Email é obrigatório e deve ser válido");
        }

        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().length() != 14) {
            throw new RuntimeException("O CNPJ é obrigatório");
        }

        if (fornecedor.getRazaoSocial() == null) {
            throw new RuntimeException("O Razao Social é obrigatório e deve ser válido");
        }

        fornecedorRepository.salvar(fornecedor);
    }

    public void atualizar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("ERRO: Fornecedor não pode ser nulo!");
        }

        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
            throw new RuntimeException("O Nome é obrigatório");
        }

        if (fornecedor.getTelefone() == null || fornecedor.getTelefone().trim().isEmpty()) {
            throw new RuntimeException("O Telefone é obrigatório");
        }

        if (fornecedor.getEmail() == null || !fornecedor.getEmail().contains("@")) {
            throw new RuntimeException("O Email é obrigatório e deve ser válido");
        }

        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().length() != 14) {
            throw new RuntimeException("O CNPJ é obrigatório");
        }

        if (fornecedor.getRazaoSocial() == null || !fornecedor.getRazaoSocial().trim().isEmpty()) {
            throw new RuntimeException("O Razao Social é obrigatório e deve ser válido");
        }

        fornecedorRepository.atualizar(fornecedor);
    }

    public void delete(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("ERRO: Fornecedor não pode ser nulo!");
        }
        if (buscarPorId(fornecedor.getId()) == null) {
            throw new IllegalArgumentException("ERRO: Fornecedor não existente!");
        }

        fornecedorRepository.delete(fornecedor);
    }

    public Fornecedor buscarPorId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ERRO: ID deve ser maior que 0");
        }

        return fornecedorRepository.buscarPorId(id);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.listarTodos();
    }
}
