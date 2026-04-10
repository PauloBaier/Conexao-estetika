package service;

import model.Fornecedor;
import model.Produto;
import repository.ProdutoRepository;
import jakarta.persistence.*;

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

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório!");
        }

        if (produto.getPrecoCompra() <= 0) {
            throw new IllegalArgumentException("Preço de compra deve ser maior que zero!");
        }

        if (produto.getPrecoVenda() <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser maior que zero!");
        }

        if (produto.getPrecoVenda() < produto.getPrecoCompra()) {
            throw new IllegalArgumentException("Preço de venda não pode ser menor que o preço de compra!");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo!");
        }

        if (produto.getEstoqueMinimo() < 0) {
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo!");
        }

        if (produto.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória!");
        }

        produtoRepository.salvar(produto);
    }

    public void atualizar(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("ERRO: Produto não pode ser nulo!");
        }

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório!");
        }

        if (produto.getPrecoCompra() <= 0) {
            throw new IllegalArgumentException("Preço de compra deve ser maior que zero!");
        }

        if (produto.getPrecoVenda() <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser maior que zero!");
        }

        if (produto.getPrecoVenda() < produto.getPrecoCompra()) {
            throw new IllegalArgumentException("Preço de venda não pode ser menor que o preço de compra!");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo!");
        }

        if (produto.getEstoqueMinimo() < 0) {
            throw new IllegalArgumentException("Estoque mínimo não pode ser negativo!");
        }

        if (produto.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória!");
        }

        if (produto.getFornecedores() == null || produto.getFornecedores().isEmpty()) {
            throw new IllegalArgumentException("Produto deve ter pelo menos um fornecedor!");
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

    public void adicionarEstoque(long id, int quantidade) {

        Produto produto = buscarPorId(id);

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado!");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero!");
        }

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new RuntimeException("Produto inválido (sem nome)!");
        }

        if (produto.getPrecoCompra() <= 0) {
            throw new RuntimeException("Preço de compra inválido!");
        }

        if (produto.getPrecoVenda() <= 0) {
            throw new RuntimeException("Preço de venda inválido!");
        }

        if (produto.getPrecoVenda() < produto.getPrecoCompra()) {
            throw new RuntimeException("Preço de venda não pode ser menor que o custo!");
        }

        produto.setQuantidadeEstoque(
                produto.getQuantidadeEstoque() + quantidade
        );

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

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado!");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero!");
        }

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new RuntimeException("Produto inválido (sem nome)!");
        }

        if (produto.getPrecoCompra() < 0) {
            throw new RuntimeException("Preço de compra inválido!");
        }

        if (produto.getPrecoVenda() <= 0) {
            throw new RuntimeException("Preço de venda inválido!");
        }

        if (produto.getPrecoVenda() < produto.getPrecoCompra()) {
            throw new RuntimeException("Preço de venda não pode ser menor que o custo!");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new RuntimeException("Estoque atual inválido!");
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