package services;

import models.Fornecedor;
import models.Produto;
import repositories.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvar(Produto produto) {
        validarProdutoCadastro(produto);
        produtoRepository.salvar(produto);
    }

    public void atualizar(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }

        Produto existente = produtoRepository.buscarPorId(produto.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        validarProdutoAtualizacao(produto);
        produtoRepository.atualizar(produto);
    }

    public void delete(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }

        if (produtoRepository.buscarPorId(produto.getId()) == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        produtoRepository.delete(produto);
    }

    public Produto buscarPorId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que 0.");
        }

        return produtoRepository.buscarPorId(id);
    }

    public List<Produto> listarTodos() {
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

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        produtoRepository.atualizar(produto);
    }

    public void removerEstoque(long id, int quantidade) {
        Produto produto = buscarPorId(id);

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado!");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero!");
        }

        if (quantidade > produto.getQuantidadeEstoque()) {
            throw new RuntimeException("Quantidade retirada não pode ser maior que a quantidade em estoque!");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        produtoRepository.atualizar(produto);
    }

    public void linkarFornecedor(long produtoId, Fornecedor fornecedor) {
        Produto produto = buscarPorId(produtoId);

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado!");
        }

        if (fornecedor == null || fornecedor.getId() == null) {
            throw new RuntimeException("Fornecedor inválido!");
        }

        produto.adicionarFornecedor(fornecedor);
        produtoRepository.atualizar(produto);
    }

    public void removerFornecedor(long produtoId, Fornecedor fornecedor) {
        Produto produto = buscarPorId(produtoId);

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado!");
        }

        if (fornecedor == null || fornecedor.getId() == null) {
            throw new RuntimeException("Fornecedor inválido!");
        }

        produto.removerFornecedor(fornecedor);
        produtoRepository.atualizar(produto);
    }

    private void validarProdutoCadastro(Produto produto) {
        validarCamposBasicos(produto);

        if (produto.getFornecedores() == null || produto.getFornecedores().isEmpty()) {
            throw new IllegalArgumentException("Produto deve ter pelo menos um fornecedor!");
        }
    }

    private void validarProdutoAtualizacao(Produto produto) {
        validarCamposBasicos(produto);
    }

    private void validarCamposBasicos(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo!");
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
    }
}