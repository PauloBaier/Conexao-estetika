package org.conexaoestetika.services;

import org.conexaoestetika.models.Fornecedor;
import org.conexaoestetika.repositories.FornecedorRepository;

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

        fornecedorRepository.salvar(fornecedor);
    }

    public void atualizar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("ERRO: Fornecedor não pode ser nulo!");
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
