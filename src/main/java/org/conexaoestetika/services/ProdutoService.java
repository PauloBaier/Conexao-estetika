package org.conexaoestetika.services;

import org.conexaoestetika.models.Fornecedor;
import org.conexaoestetika.models.Produto;
import org.conexaoestetika.repositories.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public void salvar(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("ERRO: Produto não pode ser nulo!");
        }

        produtoRepository.salvar(produto);
    }

    public void atualizar(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("ERRO: Produto não pode ser nulo!");
        }

        produtoRepository.atualizar(produto);
    }

    public void delete(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("ERRO: Produto não pode ser nulo!");
        }
        if(buscarPorId(produto.getId()) == null){
            throw new IllegalArgumentException("ERRO: Produto não existente!");
        }

        produtoRepository.delete(produto);
    }

    public Produto buscarPorId(long id){
        if(id <= 0){
            throw new IllegalArgumentException("ERRO: ID deve ser maior que 0");
        }

        return produtoRepository.buscarPorId(id) ;
    }

    public List<Produto> listarTodos(){
        return produtoRepository.listarTodos();
    }

    public void adicionarEstoque(long id, int quantidade){
        Produto produto = buscarPorId(id);

        if(produto == null){
            throw new RuntimeException("Produto não encontrado!");
        }

        if(quantidade < 0){
            throw new IllegalArgumentException("ERRO: Quantidade não pode ser negativo!");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        produtoRepository.atualizar(produto);
    }

    public void removerEstoque(long id, int quantidade){
        Produto produto = buscarPorId(id);

        if(produto == null){

            throw new RuntimeException("Produto não encontrado!");
        }

        if(quantidade < 0){
            throw new IllegalArgumentException("ERRO: Quantidade não pode ser negativo!");
        }

        if(quantidade > produto.getQuantidadeEstoque()){
            throw new RuntimeException("ERRO: Quantidade retirada não pode ser maior que a quantidade em estoque!");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        produtoRepository.atualizar(produto);
    }

    public void linkarFornecedor(long produtoId, Fornecedor fornecedor){
        Produto produto = buscarPorId(produtoId);

        if(produto == null){
            throw new RuntimeException("ERRO: Produto não encontrado!");
        }

        produto.adicionarFornecedor(fornecedor);
        produtoRepository.atualizar(produto);
    }
    public void removerFornecedor(long produtoId, Fornecedor fornecedor){
        Produto produto = buscarPorId(produtoId);

        if(produto == null){
            throw new RuntimeException("ERRO: Produto não encontrado!");
        }

        produto.removerFornecedor(fornecedor);
        produtoRepository.atualizar(produto);
    }


}
