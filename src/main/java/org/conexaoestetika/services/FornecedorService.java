package org.conexaoestetika.services;

import org.conexaoestetika.models.Fornecedor;
import org.conexaoestetika.repositories.FornecedorRepository;
import org.conexaoestetika.repositories.ProdutoRepository;

import java.util.List;

public class FornecedorService {
    FornecedorRepository fornecedorRepository;
    ProdutoRepository produtoRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository, ProdutoRepository produtoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
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
        if(produtoRepository.cadastroFornecedorEstaSendoUsado(fornecedor.getId())){
            throw new RuntimeException("ERRO: Cadastro não pode ser excluido pois está sendo usado por Produtos!");
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
